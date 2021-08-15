package org.cleanarch.todolist.domain;

import lombok.NonNull;
import lombok.Value;
import org.cleanarch.todolist.domain.TodoList.TodoListId;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoList implements AggregateRoot<TodoList, TodoListId> {
    private final TodoListId id;
    private final List<Task> tasks;

    @Value(staticConstructor = "of")
    public static class TodoListId implements Identifier {
        @NonNull
        UUID uuid;
    }

    public TodoList() {
        this.id = TodoListId.of(UUID.randomUUID());
        this.tasks = new ArrayList<>();
    }

    @Override
    public TodoListId getId() {
        return id;
    }

    public int size() {
        return tasks.size();
    }

}
