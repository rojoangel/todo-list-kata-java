package org.cleanarch.todolist.adapter.spring.persistence;

import org.cleanarch.todolist.domain.Task;
import org.cleanarch.todolist.domain.Task.TaskId;
import org.cleanarch.todolist.domain.TodoList;
import org.cleanarch.todolist.domain.TodoList.TodoListId;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TodoListMapper {

    TodoList todoListEntityToTodoList(TodoListEntity entity);

    TodoListEntity todoListToTodoListEntity(TodoList todoList);

    Task taskEntityToTask(TaskEntity entity);

    TaskEntity taskToTaskEntity(Task task);

    default TodoListId uuidToTodoListId(UUID uuid) {
        return TodoListId.of(uuid);
    }

    default TaskId uuidToTaskId(UUID uuid) {
        return TaskId.of(uuid);
    }

    default UUID todoListIdToUuid(TodoListId todoListId) {
        return todoListId.getUuid();
    }

    default UUID taskIdToUuid(TaskId taskId) {
        return taskId.getUuid();
    }
}
