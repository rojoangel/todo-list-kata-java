package org.cleanarch.todolist.usecase.response;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class FindOrCreateListResponse {

    @NonNull
    UUID todoListId;
}
