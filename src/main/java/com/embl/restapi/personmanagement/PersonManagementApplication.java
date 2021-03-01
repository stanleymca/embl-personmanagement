package com.embl.restapi.personmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan (basePackages = "com.embl.restapi.personmanagement")
@SpringBootApplication
public class PersonManagementApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(PersonManagementApplication.class, args);
	}
}
