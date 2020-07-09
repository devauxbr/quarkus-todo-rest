package io.devaux.todo.web;

import io.devaux.todo.persistence.TodoRepository;
import io.devaux.todo.persistence.entity.Todo;
import io.devaux.todo.service.TodoService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/admin/todo")
@Authenticated
public class AdminResource {

    @Inject
    TodoRepository repository;

    @Inject
    TodoService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Todo create(Todo todo) {
        return service.create(todo);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }

}
