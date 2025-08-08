package org.pado.api.service;

import org.pado.api.domain.user.User;
import org.pado.api.domain.user.UserRepository;
import org.pado.api.dto.request.UserLoginRequest;
import org.pado.api.dto.response.UserLoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final TokenBlacklistService tokenBlacklistService;

    /**
     * 로그인 - 보안 로깅 적용
     * @param request 로그인 요청 DTO
     * @return 로그인 응답 DTO (JWT 토큰 포함)
     */
    @Transactional(readOnly = true)
    public UserLoginResponse signin(UserLoginRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_AUTHENTICATION_FAILED, 
                    "사용자명 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_AUTHENTICATION_FAILED, 
                "사용자명 또는 비밀번호가 올바르지 않습니다.");
        }

        // Spring Security 인증 처리 (아이디 기반)
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), request.getPassword())
            );
        } catch (Exception e) {
            log.warn("Spring Security 인증 실패: {}", e.getMessage());
            throw new CustomException(ErrorCode.USER_AUTHENTICATION_FAILED, 
                "인증 처리 중 오류가 발생했습니다.");
        }
        
        // 사용자 ID로 JWT 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(user.getId().toString());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId().toString());
        
        return new UserLoginResponse(accessToken, refreshToken);
    }
}
