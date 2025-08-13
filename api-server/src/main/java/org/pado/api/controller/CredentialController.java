package org.pado.api.controller;

import org.pado.api.core.security.userdetails.CustomUserDetails;
import org.pado.api.dto.request.CredentialRegisterRequest;
import org.pado.api.dto.response.CredentialResponse;
import org.pado.api.service.CredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Credential 관리 (등록)", description = "특정 유저에 대해 자격 증명을 등록합니다.")
    @PostMapping
    public ResponseEntity<CredentialResponse> createCredential(
            @RequestBody CredentialRegisterRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(credentialService.createCredential(request, userDetails.getId()));
    }
    
}
