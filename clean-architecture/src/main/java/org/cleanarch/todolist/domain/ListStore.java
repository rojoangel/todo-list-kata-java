package org.cleanarch.todolist.domain;

import org.cleanarch.todolist.domain.TodoList.TodoListId;
import org.jmolecules.ddd.types.Repository;

public interface ListStore extends Repository<TodoList, TodoListId> {

    Iterable<TodoList> findAll();

    TodoList save(TodoList todoList);
}
