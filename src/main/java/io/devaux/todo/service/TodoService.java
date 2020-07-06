package io.devaux.todo.service;

import io.devaux.todo.model.ExpireAtHolder;
import io.devaux.todo.persistence.TodoRepository;
import io.devaux.todo.persistence.entity.Todo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
@Transactional
public class TodoService {

    @Inject
    TodoRepository repository;

    @Inject
    EntityManager em;

    public Todo create(Todo todo) {
        repository.persistAndFlush(todo);
        return todo;
    }

    public void update(Todo todo) {
        findOrThrow(todo.getId());
        em.merge(todo);
    }

    public void setExpired(Long id, ExpireAtHolder command) {
        Todo exisitingTodo = findOrThrow(id);
        exisitingTodo.setExpireAt(command.getExpireAt());
        repository.persist(exisitingTodo);
    }

    public void setDone(Long id) {
        Todo exisitingTodo = findOrThrow(id);
        exisitingTodo.setDoneAt(new Date());
        repository.persist(exisitingTodo);
    }

    private Todo findOrThrow(Long id) {
        Todo exisitingTodo = repository.findById(id);
        if (exisitingTodo == null) {
            throw new IllegalArgumentException("No Todo found with ID " + id);
        }
        return exisitingTodo;
    }
}
