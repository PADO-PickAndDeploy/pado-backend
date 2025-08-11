package org.pado.api.core.security.jwt;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Profile("ci")
public class CiTokenBlacklistService implements TokenBlacklistService {
    @Override
    public void blacklistToken(String token) {
        // CI 환경: NoOp 구현
    }
    
    @Override
    public boolean isBlacklisted(String token) {
        return false; // 항상 허용 (블랙리스트 기능 없음)
    }
}
