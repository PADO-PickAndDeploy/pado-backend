package org.pado.api.service;

import org.pado.api.domain.credential.CredentialRepository;
import org.pado.api.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {
    
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    
    
}
