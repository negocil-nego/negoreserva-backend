package com.negoreserva;

import com.negoreserva.common.component.CorsProperties;
import com.negoreserva.common.component.RsaKeyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyProperties.class, CorsProperties.class})
public class NegoreservaApplication {
	static void main(String[] args) {
		SpringApplication.run(NegoreservaApplication.class, args);
	}
}
