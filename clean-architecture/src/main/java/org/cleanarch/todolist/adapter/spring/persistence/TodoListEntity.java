package org.cleanarch.todolist.adapter.spring.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor // JPA needs default constructor
@AllArgsConstructor
@Getter
@Setter
public class TodoListEntity {

    @Id
    private UUID id;

    @OneToMany
    private List<TaskEntity> tasks;
}
