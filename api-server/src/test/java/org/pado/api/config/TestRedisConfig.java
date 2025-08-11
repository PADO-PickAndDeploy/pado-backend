package org.pado.api.config;

import org.pado.api.core.security.jwt.TokenBlacklistService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration  
public class TestRedisConfig {
    @Bean
    @Primary
    public TokenBlacklistService tokenBlacklistService() {
        return new TokenBlacklistService() {
            @Override
            public void blacklistToken(String token) {
                
            }
            
            @Override
            public boolean isBlacklisted(String token) {
                return false;
            }
        };
    }
}
