package com.bfc.appmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AppmgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppmgmtApplication.class, args);
	}

}
