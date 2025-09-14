package com.site.patinha_feliz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.site.patinha_feliz", "com.site.patinha_feliz.controllers"})
public class PatinhaFelizApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatinhaFelizApplication.class, args);
	}

}
