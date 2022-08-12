package com.conygre.backendTampa4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.conygre.backendTampa4")
public class BackendTampa4Application {

	public static void main(String[] args) {
		SpringApplication.run(BackendTampa4Application.class, args);
	}

}
