package com.organization.software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories
public class SoftwareApplication {
	private static final Logger logger = LoggerFactory.getLogger(SoftwareApplication.class);
	public static void main(String[] args) {
		logger.info("Program start");
		SpringApplication.run(SoftwareApplication.class, args);
	}

}
