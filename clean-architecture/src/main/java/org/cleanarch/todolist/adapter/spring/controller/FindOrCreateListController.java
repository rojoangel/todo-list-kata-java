package org.cleanarch.todolist.adapter.spring.controller;

import org.cleanarch.todolist.usecase.FindOrCreateList;
import org.cleanarch.todolist.usecase.request.FindOrCreateListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FindOrCreateListController {

    private final FindOrCreateList findOrCreateList;

    @Autowired
    public FindOrCreateListController(FindOrCreateList findOrCreateList) {
        this.findOrCreateList = findOrCreateList;
    }

    @GetMapping("/todolist")
    public UUID execute() {
        final var findOrCreateListRequest = new FindOrCreateListRequest();
        final var findOrCreateListResponse = findOrCreateList.apply(findOrCreateListRequest);
        return findOrCreateListResponse.getTodoListId();
    }
}
