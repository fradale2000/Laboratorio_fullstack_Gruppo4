package com.team2.itsincom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ItsincomApplication {
	
	public static final Logger LOGGER=LoggerFactory.getLogger(ItsincomApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ItsincomApplication.class, args);
	}
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
