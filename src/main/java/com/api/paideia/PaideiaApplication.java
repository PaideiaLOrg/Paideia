package com.api.paideia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.api.paideia.repositories")
public class PaideiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaideiaApplication.class, args);
	}

}
