package org.pado.api;

import java.util.Optional;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.core.VaultTemplate;

@TestConfiguration
@Profile("ci")
public class TestVaultConfig {

    /**
     * CI 환경에서는 빈 Optional 제공
     */
    @Bean
    @Primary
    public Optional<VaultTemplate> ciVaultTemplate() {
        return Optional.empty();
    }
}
