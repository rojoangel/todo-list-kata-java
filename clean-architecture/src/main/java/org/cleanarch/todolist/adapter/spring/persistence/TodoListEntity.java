package org.cleanarch.todolist.adapter.spring.persistence;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cleanarch.todolist.domain.Task;
import org.cleanarch.todolist.domain.TodoList;
import org.cleanarch.todolist.domain.TodoList.TodoListId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.cleanarch.todolist.domain.Task.TaskId;

@Entity
@NoArgsConstructor // JPA needs default constructor
@AllArgsConstructor
public class TodoListEntity {

    @Id
    private UUID id;

    @OneToMany
    private List<TaskEntity> tasks;

    public TodoList toModel() {
        return new TodoList(
                TodoListId.of(id),
                tasks.stream()
                        .map(t -> new Task(TaskId.of(t.getId()), t.getName(), t.isCompleted()))
                        .collect(Collectors.toList())
        );
    }

    public static TodoListEntity from(TodoList todoList) {
        return new TodoListEntity(
                todoList.getId().getUuid(),
                todoList.listTasks().stream()
                        .map(t -> new TaskEntity(t.getId().getUuid(), t.getName(), t.isCompleted()))
                        .collect(Collectors.toList())
        );
    }
}
