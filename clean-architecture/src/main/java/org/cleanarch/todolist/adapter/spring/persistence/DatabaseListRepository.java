package org.cleanarch.todolist.adapter.spring.persistence;

import org.cleanarch.todolist.domain.ListStore;
import org.cleanarch.todolist.domain.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseListRepository implements ListStore {

    private final SpringJpaListRepository listRepository;

    @Autowired
    public DatabaseListRepository(SpringJpaListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public Iterable<TodoList> findAll() {
        List<TodoListEntity> allTodoLists = listRepository.findAll();
        return allTodoLists.stream()
                .map(TodoListEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public TodoList save(TodoList todoList) {
        listRepository.save(TodoListEntity.from(todoList));
        return todoList;
    }
}
