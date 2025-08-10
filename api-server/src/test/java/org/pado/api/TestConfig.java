package org.pado.api;

import org.pado.api.core.security.jwt.TokenBlacklistService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class TestConfig {
    @Bean
    @Profile("ci")
    @Primary
    public TokenBlacklistService testTokenBlacklistService() {
        return new TokenBlacklistService() {
            @Override
            public void blacklistToken(String token) {
                // 테스트용 NoOp
            }

            @Override
            public boolean isBlacklisted(String token) {
                return false;
            }
        };
    }
}
