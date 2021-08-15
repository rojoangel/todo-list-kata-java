package org.cleanarch.todolist.adapter.spring.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor // JPA needs default constructor
@AllArgsConstructor
@Getter
@Setter
public class TaskEntity {

    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    private boolean completed;
}
