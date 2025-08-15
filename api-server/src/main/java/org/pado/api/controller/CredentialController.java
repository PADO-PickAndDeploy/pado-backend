package org.pado.api.controller;

import org.pado.api.core.security.userdetails.CustomUserDetails;
import org.pado.api.dto.request.CredentialRegisterRequest;
import org.pado.api.dto.response.CredentialResponse;
import org.pado.api.dto.response.DefaultResponse;
import org.pado.api.service.CredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/credentials")
@RequiredArgsConstructor
@Tag(name = "Credential", description = "자격 증명 관련 API")
@SecurityRequirement(name = "bearerAuth")
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping
    @Operation(
        summary = "크리덴셜 등록", 
        description = "새로운 크리덴셜을 등록합니다. 외부 API를 통해 유효성을 검증한 후 Vault에 안전하게 저장됩니다.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "크리덴셜 등록 성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CredentialResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "요청 데이터 오류 (필수 필드 누락, 잘못된 형식, 필드 길이 초과, 유효하지 않은 크리덴셜)",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "인증 오류",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "중복된 크리덴셜 이름",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "서버 내부 오류 (Vault 암호화 실패, DB 연결 오류)",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        )
    })
    public ResponseEntity<CredentialResponse> createCredential(
            @RequestBody CredentialRegisterRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
                return ResponseEntity.ok(credentialService.createCredential(request, userDetails));
    }
    
}
