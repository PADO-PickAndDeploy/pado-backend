package org.pado.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PadoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadoServerApplication.class, args);
	}

}
