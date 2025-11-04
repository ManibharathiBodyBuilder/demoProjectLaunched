package com.DemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableScheduling
@EnableCaching
public class DemoProjectApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("./")        // .env file at project root
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		System.setProperty("DB_URL", dotenv.get("DB_URL", ""));
		System.setProperty("DB_USER", dotenv.get("DB_USER", ""));
		System.setProperty("DB_PASS", dotenv.get("DB_PASS", ""));
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
