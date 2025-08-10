package org.pado.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ci")
@Import(TestConfig.class)
class PadoServerApplicationTests {

	@Test
	void contextLoads() {
	}

}
