package io.devaux.todo.persistence;

import io.devaux.todo.persistence.entity.Todo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TodoRepository implements PanacheRepository<Todo> {
}
