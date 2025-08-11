package org.pado.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.NONE,
	// 로그 확인 용으로 추가
	properties = {
        "logging.level.org.springframework.beans.factory=DEBUG",
        "logging.level.org.springframework.context=DEBUG",
        "logging.level.org.springframework.boot.autoconfigure=DEBUG",
        "logging.level.org.pado=DEBUG"
    }
)
@ActiveProfiles("ci")
class PadoServerApplicationTests {

	@Test
	void contextLoads() {
		// 컨텍스트 로딩 테스트
	}
}