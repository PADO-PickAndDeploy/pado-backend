package org.pado.api.controller;

import org.pado.api.service.CredentialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
