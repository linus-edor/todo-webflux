package com.boot.todo.webflux.functional.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import com.boot.todo.webflux.model.ToDo;
import com.boot.todo.webflux.repository.ToDoRepository;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ToDoHandler {
	private ToDoRepository repository;

	public ToDoHandler(ToDoRepository repository) {
		this.repository = repository;
	}

	public Mono<ServerResponse> getToDo(ServerRequest request) {
		String toDoId = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<ToDo> toDo = this.repository.findById(toDoId);
		return toDo.flatMap(t -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(t)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> getToDos(ServerRequest request) {
		Flux<ToDo> toDos = this.repository.findAll();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(toDos, ToDo.class);
	}
}