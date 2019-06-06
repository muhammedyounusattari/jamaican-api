package com.pica.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.pica.*")
@EnableWebMvc
@EnableMongoRepositories("com.pica.dao")
public class JamaicaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JamaicaApiApplication.class, args);
	}

}
