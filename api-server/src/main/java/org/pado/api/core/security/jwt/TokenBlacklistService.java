package org.pado.api.core.security.jwt;
/**
 * 토큰 블랙리스트 관리 인터페이스
 * Redis 환경과 비Redis 환경에서 다른 구현체 사용
 */
public interface TokenBlacklistService {
    /**
     * 토큰을 블랙리스트에 추가
     * @param token 블랙리스트에 추가할 토큰
     * @param expiration 토큰 만료 시간
     */
    void blacklistToken(String token);

    /**
     * 토큰이 블랙리스트에 있는지 확인
     * @param token 확인할 토큰
     * @return 블랙리스트에 있으면 true, 없으면 false
     */
    boolean isBlacklisted(String token);
}   