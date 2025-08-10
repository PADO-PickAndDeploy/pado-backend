package org.pado.api;

import org.pado.api.core.security.jwt.TokenBlacklistService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary // 테스트에서 최우선으로 사용
    public TokenBlacklistService testTokenBlacklistService() {
        return new TokenBlacklistService() {
            @Override
            public void blacklistToken(String token) {
                // CI 테스트용 NoOp 구현 - 실제 처리하지 않음
            }

            @Override
            public boolean isBlacklisted(String token) {
                // CI 테스트용 - 항상 false 반환
                return false;
            }
        };
    }
}
