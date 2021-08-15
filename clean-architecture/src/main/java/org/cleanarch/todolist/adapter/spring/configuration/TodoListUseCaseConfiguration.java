package org.cleanarch.todolist.adapter.spring.configuration;

import org.cleanarch.todolist.domain.ListStore;
import org.cleanarch.todolist.usecase.FindOrCreateList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoListUseCaseConfiguration {

    @Bean
    FindOrCreateList findOrCreateList(ListStore listStore) {
        return new FindOrCreateList(listStore);
    }
}
