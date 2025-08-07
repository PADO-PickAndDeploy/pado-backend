package org.pado.api.domain.credential;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findByUserId(Long userId);
    Credential findByName(String name);    
    Credential findByType(String type);
}
