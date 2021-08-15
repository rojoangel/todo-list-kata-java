package org.cleanarch.todolist.usecase;

import org.cleanarch.todolist.domain.ListStore;
import org.cleanarch.todolist.domain.TodoList;
import org.cleanarch.todolist.usecase.request.FindOrCreateListRequest;
import org.cleanarch.todolist.usecase.response.FindOrCreateListResponse;

import java.util.Iterator;
import java.util.function.Function;

public class FindOrCreateList implements Function<FindOrCreateListRequest, FindOrCreateListResponse> {
    private final ListStore listStore;

    public FindOrCreateList(ListStore listStore) {
        this.listStore = listStore;
    }

    @Override
    public FindOrCreateListResponse apply(FindOrCreateListRequest findOrCreateListRequest) {
        final Iterator<TodoList> allListsIterator = listStore.findAll().iterator();
        var todoList = allListsIterator.hasNext() ? allListsIterator.next() : listStore.save(new TodoList());
        return new FindOrCreateListResponse(todoList.getId().getUuid());
    }
}
