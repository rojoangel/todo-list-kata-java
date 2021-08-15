package org.cleanarch.todolist.domain;

public class TodoListException extends RuntimeException {

    public TodoListException(String message) {
        super(message);
    }
}
