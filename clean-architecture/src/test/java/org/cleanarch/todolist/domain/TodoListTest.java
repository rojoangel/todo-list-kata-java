package org.cleanarch.todolist.domain;

import org.cleanarch.todolist.domain.Task.TaskId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TodoListTest {

    public static final String TASK_ONE = "TaskOne";
    private static final String TASK_TWO = "TaskTwo";

    @Test
    void should_create_empty_list() {
        final var list = new TodoList();
        assertThat(list.listTasks().size(), is(equalTo(0)));
    }

    @Test
    void should_add_task() {
        final var list = new TodoList();
        list.addTask(TASK_ONE);
        assertThat(list.listTasks().size(), is(equalTo(1)));
    }

    @Test
    void should_add_two_tasks() {
        final var list = new TodoList();
        list.addTask(TASK_ONE);
        list.addTask(TASK_TWO);
        assertThat(list.listTasks().size(), is(equalTo(2)));
        assertThat(list.listTasks(), contains(
                hasProperty("name", is(equalTo(TASK_ONE))),
                hasProperty("name", is(equalTo(TASK_TWO)))
        ));
    }

    @Test
    void should_not_add_a_task_without_name() {
        final var list = new TodoList();
        assertThrows(InvalidTaskName.class, () -> list.addTask(null));
        assertThat(list.listTasks().size(), is(equalTo(0)));
    }

    @Test
    void should_not_add_a_task_with_empty_name() {
        final var list = new TodoList();
        assertThrows(InvalidTaskName.class, () -> list.addTask(""));
        assertThat(list.listTasks().size(), is(equalTo(0)));
    }

    @Test
    void should_not_add_a_task_with_all_whitespaces_name() {
        final var list = new TodoList();
        assertThrows(InvalidTaskName.class, () -> list.addTask("  "));
        assertThat(list.listTasks().size(), is(equalTo(0)));
    }

    @Test
    void should_delete_task() {
        final var list = new TodoList();
        list.addTask(TASK_ONE);
        TaskId taskId = list.addTask(TASK_TWO);

        list.deleteTask(taskId);

        assertThat(list.listTasks().size(), is(equalTo(1)));
        assertThat(list.listTasks(), not(contains(hasProperty("name", is(equalTo(TASK_TWO))))));
    }

    @Test
    void should_not_fail_when_deleting_inexistent_task() {
        final var list = new TodoList();

        final UUID uuid = UUID.randomUUID();
        assertDoesNotThrow(() -> list.deleteTask(TaskId.of(uuid)));
    }

    @Test
    void should_add_new_task_as_not_completed() {
        final var list = new TodoList();

        list.addTask(TASK_ONE);

        assertThat(list.listTasks(), contains(hasProperty("completed", is(equalTo(false)))));
    }

    @Test
    void should_complete_task() {
        final var list = new TodoList();
        TaskId taskId = list.addTask(TASK_ONE);

        list.toogleTaskCompletion(taskId);

        assertThat(list.listTasks(), contains(hasProperty("completed", is(equalTo(true)))));
    }
}
