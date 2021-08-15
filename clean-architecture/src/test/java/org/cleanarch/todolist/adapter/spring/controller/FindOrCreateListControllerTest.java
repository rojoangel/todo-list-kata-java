package org.cleanarch.todolist.adapter.spring.controller;

import org.cleanarch.todolist.domain.ListStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindOrCreateListControllerTest {

    private final TestRestTemplate restTemplate;
    private final ListStore listStore;

    @Autowired
    public FindOrCreateListControllerTest(TestRestTemplate restTemplate, ListStore listStore) {
        this.restTemplate = restTemplate;
        this.listStore = listStore;
    }

    @Test
    void should_create_list() {
        ResponseEntity<UUID> response = restTemplate.exchange(
                "/todolist",
                HttpMethod.GET,
                new HttpEntity<>(null),
                UUID.class);

        assertThat(listStore.findAll().iterator().next().getId().getUuid(), equalTo(response.getBody()));
    }
}