package org.pado.api;

import org.junit.jupiter.api.Test;
import org.pado.api.core.security.jwt.TokenBlacklistService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("ci")
@Import(PadoServerApplicationTests.TestConfigForCI.class)
class PadoServerApplicationTests {

	@Test
	void contextLoads() {
		// 컨텍스트 로딩 테스트
	}

	@TestConfiguration
	static class TestConfigForCI {

		@Bean
		@Primary
		public TokenBlacklistService testTokenBlacklistService() {
			return new TokenBlacklistService() {
				@Override
				public void blacklistToken(String token) {
					// NoOp implementation for CI
				}

				@Override
				public boolean isBlacklisted(String token) {
					return false;
				}
			};
		}
	}

}