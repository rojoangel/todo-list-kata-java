package org.cleanarch.todolist.domain;

import lombok.NonNull;
import lombok.Value;
import org.cleanarch.todolist.domain.TodoList.TodoListId;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public class TodoList implements AggregateRoot<TodoList, TodoListId> {
    private final TodoListId id;

    @Value(staticConstructor = "of")
    public static class TodoListId implements Identifier {
        @NonNull
        UUID uuid;
    }

    public TodoList() {
        this.id = TodoListId.of(UUID.randomUUID());
    }

    @Override
    public TodoListId getId() {
        return id;
    }
}
