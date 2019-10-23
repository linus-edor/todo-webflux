package com.boot.todo.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TodoWebfluxApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		app.run(args);
	}

}
