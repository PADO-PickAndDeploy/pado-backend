package org.pado.api.controller;

import java.util.Map;

import org.pado.api.dto.request.UserLoginRequest;
import org.pado.api.dto.response.DefaultResponse;
import org.pado.api.dto.response.UserLoginResponse;
import org.pado.api.dto.request.SignupRequest;
import org.pado.api.dto.response.DefaultResponse;
import org.pado.api.dto.response.SignupResponse;
import org.pado.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "유저 정보를 기반으로 회원가입을 진행합니다.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "회원가입 성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SignupResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "잘못된 요청 (유효성 검증 실패, 중복된 이메일 등)",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "이미 존재하는 사용자",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "서버 내부 오류",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        )
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }
    
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인을 시도합니다.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "로그인 성공",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserLoginResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "잘못된 요청 (필수 필드 누락 등)",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "인증 실패 (잘못된 이메일 또는 비밀번호)",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "존재하지 않는 사용자",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "서버 내부 오류",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DefaultResponse.class)
            )
        )
    })
    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponse> signin(@Valid @RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }

}
