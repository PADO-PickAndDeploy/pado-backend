package org.pado.api.domain.credential;

import java.util.List;

import org.pado.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    List<Credential> findByUser(User user);
    List<Credential> findByUserId(Long userId);
    List<Credential> findByName(String name);    
    List<Credential> findByType(String type);
    boolean existsByNameAndUser(String name, User user);
}
