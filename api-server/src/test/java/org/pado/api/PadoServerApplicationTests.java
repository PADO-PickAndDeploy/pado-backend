package org.pado.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("ci")
class PadoServerApplicationTests {

	@Test
	void contextLoads() {
		try {
			System.out.println("=== 컨텍스트 로딩 시작 ===");
			// 컨텍스트 로딩 완료되면 여기까지 옴
			System.out.println("=== 컨텍스트 로딩 성공 ===");
		} catch (Exception e) {
			System.err.println("=== 에러 발생 ===");
			System.err.println("Error: " + e.getClass().getSimpleName());
			System.err.println("Message: " + e.getMessage());
			e.printStackTrace();
			throw e; // 다시 던져서 테스트 실패하게 함
		}
	}
}