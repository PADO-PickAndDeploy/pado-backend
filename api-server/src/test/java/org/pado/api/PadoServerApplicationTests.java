package org.pado.api;

import org.junit.jupiter.api.Test;
import org.pado.api.config.TestRedisConfig;
import org.pado.api.config.TestSecurityConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("ci")
@Import({TestSecurityConfig.class, TestRedisConfig.class})
class PadoServerApplicationTests {

	@Test
	void contextLoads() {

	}
}