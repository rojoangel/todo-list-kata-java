package org.cleanarch.todolist.domain;

import lombok.NonNull;
import lombok.Value;
import org.cleanarch.todolist.domain.Task.TaskId;
import org.cleanarch.todolist.domain.TodoList.TodoListId;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.util.ArrayList;
import java.util.Collections;
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

    public TaskId addTask(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new InvalidTaskName("Tasks should have a non-null non-whitespace name");
        }
        Task task = new Task(TaskId.of(UUID.randomUUID()), taskName);
        tasks.add(task);
        return task.getId();
    }

    public List<Task> listTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void deleteTask(TaskId taskId) {
        tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .ifPresent(tasks::remove);
    }

    public void toogleTaskCompletion(TaskId taskId) {
        tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .ifPresent(t -> tasks.set(tasks.indexOf(t), new Task(t.getId(), t.getName(), !t.isCompleted())));
    }
}
