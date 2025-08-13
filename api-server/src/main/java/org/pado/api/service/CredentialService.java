package org.pado.api.service;

import java.time.format.DateTimeFormatter;

import org.pado.api.core.exception.CustomException;
import org.pado.api.core.exception.ErrorCode;
import org.pado.api.core.vault.service.CredentialVaultService;
import org.pado.api.domain.credential.Credential;
import org.pado.api.domain.credential.CredentialRepository;
import org.pado.api.domain.user.User;
import org.pado.api.domain.user.UserRepository;
import org.pado.api.dto.request.CredentialRegisterRequest;
import org.pado.api.dto.response.CredentialResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredentialService {
    
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final CredentialVaultService credentialVaultService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    
    @Transactional
    public CredentialResponse createCredential(CredentialRegisterRequest request, Long userId) {
        log.info("Creating credential for user: {}", userId);
        
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, 
                    "사용자를 찾을 수 없습니다."));
        
        // 크리덴셜 엔티티 생성 (vaultKey는 제거 - Vault에서 경로로 관리)
        Credential credential = Credential.builder()
                .name(request.getName())
                .type(request.getType())
                .description(request.getDescription())
                .user(user)
                .build();

        // DB에 크리덴셜 메타데이터 저장
        Credential savedCredential = credentialRepository.save(credential);

        try {
            // Vault에 실제 credentialData 저장
            credentialVaultService.storeCredentialData(user, savedCredential, request.getData());
            log.info("Successfully stored credential data in Vault for credential: {}", savedCredential.getId());
        } catch (Exception e) {
            // Vault 저장 실패시 DB 롤백을 위해 RuntimeException 던지기
            log.error("Failed to store credential data in Vault for credential: {}", savedCredential.getId(), e);
            throw new CustomException(ErrorCode.VAULT_OPERATION_FAILED, 
                "크리덴셜 데이터 저장에 실패했습니다.", e);
        }
        
        return new CredentialResponse(
                savedCredential.getId(),
                savedCredential.getName(),
                savedCredential.getCredentialType(),
                savedCredential.getDescription(),
                "크리덴셜 등록 완료",
                savedCredential.getCreatedAt().format(formatter)
        );
    }
    
}
