package org.cleanarch.todolist.usecase;

import org.cleanarch.todolist.domain.ListStore;
import org.cleanarch.todolist.domain.TodoList;
import org.cleanarch.todolist.usecase.request.FindOrCreateListRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FindOrCreateListTest {

    @Mock
    ListStore listStore;

    @Test
    void should_create_list_when_not_found() {
        MockitoAnnotations.openMocks(this);
        when(listStore.findAll()).thenReturn(Collections.emptyList());
        var todoList = new TodoList();
        when(listStore.save(any(TodoList.class))).thenReturn(todoList);

        FindOrCreateList findOrCreateList = new FindOrCreateList(listStore);
        var findOrCreateListResponse = findOrCreateList.apply(new FindOrCreateListRequest());

        assertThat(findOrCreateListResponse.getTodoListId(), is(equalTo(todoList.getId().getUuid())));
    }
}