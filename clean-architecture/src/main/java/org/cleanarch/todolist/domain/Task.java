package org.cleanarch.todolist.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.cleanarch.todolist.domain.Task.TaskId;
import org.jmolecules.ddd.types.Entity;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

@Getter
public class Task implements Entity<TodoList, TaskId> {

    private final TaskId id;
    private final String name;

    @Value(staticConstructor = "of")
    public static class TaskId implements Identifier {
        @NonNull
        UUID uuid;
    }

    @Override
    public TaskId getId() {
        return id;
    }

    Task(@NonNull TaskId id,
         @NonNull String name) {
        this.id = id;
        this.name = name;
    }
}
