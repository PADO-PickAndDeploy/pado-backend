package org.pado.api.core.security.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis가 없는 환경(CI, 테스트 등)에서 사용하는 NoOp 토큰 블랙리스트 서비스
 * 실제로는 아무 작업도 수행하지 않음
 */
@Service
@ConditionalOnMissingBean(RedisTokenBlacklistService.class)
@Slf4j
public class NoOpTokenBlacklistService implements TokenBlacklistService{
    @Override
    public void blacklistToken(String token) {
        log.debug("NoOp: 토큰 블랙리스트 추가 요청 (실제 처리 안함) - token: {}", 
                token.substring(0, Math.min(10, token.length())) + "...");
        // 아무 작업도 수행하지 않음
    }

    @Override
    public boolean isBlacklisted(String token) {
        log.debug("NoOp: 토큰 블랙리스트 확인 요청 (항상 false 반환) - token: {}", 
                token.substring(0, Math.min(10, token.length())) + "...");
        // 항상 false 반환 (블랙리스트에 없다고 가정)
        return false;
    }
}
