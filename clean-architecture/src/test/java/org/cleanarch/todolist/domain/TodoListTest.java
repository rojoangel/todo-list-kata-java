package org.cleanarch.todolist.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TodoListTest {

    public static final String TASK_ONE = "TaskOne";

    @Test
    void should_create_empty_list() {
        final var list = new TodoList();
        assertThat(list.size(), is(equalTo(0)));
    }

    @Test
    void should_add_task() {
        final var list = new TodoList();
        list.addTask(TASK_ONE);
        assertThat(list.size(), is(equalTo(1)));
    }
}