package org.cleanarch.todolist.domain;

public class InvalidTaskName extends TodoListException {

    public InvalidTaskName(String message) {
        super(message);
    }
}
