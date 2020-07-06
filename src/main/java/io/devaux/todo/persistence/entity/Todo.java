package io.devaux.todo.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private Date expireAt;
    private Date createdAt;
    private Date updatedAt;
    private Date doneAt;
    private Long version = 0L;

    @PrePersist
    void setCreatedAtOnPersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    void setUpdatedAtOnUpdate() {
        this.createdAt = new Date();
        this.version += 1;
    }
}
