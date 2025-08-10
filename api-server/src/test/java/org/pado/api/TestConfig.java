package org.pado.api;

import org.pado.api.core.security.jwt.TokenBlacklistService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import lombok.extern.slf4j.Slf4j;

@TestConfiguration
@Slf4j
public class TestConfig {
    @Bean
    @Primary // 테스트에서 최우선으로 사용
    public TokenBlacklistService testTokenBlacklistService() {
        log.info("TestConfig: TokenBlacklistService 빈 생성됨 (NoOp 구현체)");
        return new TokenBlacklistService() {
            @Override
            public void blacklistToken(String token) {
                log.debug("NoOp: 토큰 블랙리스트 추가 (실제 처리 안함)");
            }

            @Override
            public boolean isBlacklisted(String token) {
                log.debug("NoOp: 토큰 블랙리스트 확인 (항상 false 반환)");
                return false;
            }
        };
    }
}
