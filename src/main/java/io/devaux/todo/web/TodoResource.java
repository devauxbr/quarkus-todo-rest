package io.devaux.todo.web;

import io.devaux.todo.model.ExpireAtHolder;
import io.devaux.todo.persistence.entity.Todo;
import io.devaux.todo.persistence.TodoRepository;
import io.devaux.todo.service.TodoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todo")
public class TodoResource {

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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Todo todo) {
        service.update(todo);
    }

    @PUT
    @Path("/{id}/expire")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setExpired(@PathParam("id") Long id, ExpireAtHolder expireAtHolder) {
        service.setExpired(id, expireAtHolder);
    }

    @PUT
    @Path("/{id}/done")
    public void setDone(@PathParam("id") Long id) {
        service.setDone(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getAll() {
        return repository.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Todo getById(@PathParam("id") Long id) {
        return repository.findById(id);
    }
}
