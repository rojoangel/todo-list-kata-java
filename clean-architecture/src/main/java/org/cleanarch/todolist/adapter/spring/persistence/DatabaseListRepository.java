package org.cleanarch.todolist.adapter.spring.persistence;

import org.cleanarch.todolist.domain.ListStore;
import org.cleanarch.todolist.domain.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    // Transaction needs to be propagated to be able to lazily fetch tasks from list while mapping
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Iterable<TodoList> findAll() {
        List<TodoListEntity> allTodoLists = listRepository.findAll();
        return allTodoLists.stream()
                .map(TodoListEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public TodoList save(TodoList todoList) {
        return listRepository.save(TodoListEntity.from(todoList)).toModel();
    }
}
