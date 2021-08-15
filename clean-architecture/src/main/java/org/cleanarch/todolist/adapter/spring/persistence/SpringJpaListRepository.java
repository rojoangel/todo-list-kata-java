package org.cleanarch.todolist.adapter.spring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringJpaListRepository extends JpaRepository<TodoListEntity, UUID> {
}
