package ru.yaal.spring.security.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class. Choose a authentication type.
 */
@SpringBootApplication
public class SecurityApplication {
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.profiles.active", Profiles.USER_DETAIL_SERVICE);
		SpringApplication.run(SecurityApplication.class, args);
	}
}