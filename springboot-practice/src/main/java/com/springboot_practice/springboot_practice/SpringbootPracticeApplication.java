package com.springboot_practice.springboot_practice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "My API Documentation",
				version = "1.0",
				description = "This is the API documentation for my Spring Boot application."
		)
)
public class SpringbootPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPracticeApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
