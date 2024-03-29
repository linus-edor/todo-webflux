package com.boot.todo.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.boot.todo.webflux.model.ToDo;
import com.boot.todo.webflux.repository.ToDoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController     //comment out to replace controller with the functional endpoint
public class ToDoController {
	private ToDoRepository repository;

	public ToDoController(ToDoRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/todo/{id}")
	public Mono<ToDo> getToDo(@PathVariable String id) {
		return this.repository.findById(id);
	}

	@GetMapping("/todo")
	public Flux<ToDo> getToDos() {
		return this.repository.findAll();
	}
}