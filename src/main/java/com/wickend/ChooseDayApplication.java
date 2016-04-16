package com.wickend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChooseDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChooseDayApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDb() {
		return strings -> {

        };
	}
}
