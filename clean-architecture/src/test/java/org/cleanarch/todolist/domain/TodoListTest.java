package org.cleanarch.todolist.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TodoListTest {

    @Test
    void should_create_empty_list() {
        var list = new TodoList();
        assertThat(list.size(), is(equalTo(0)));
    }
}