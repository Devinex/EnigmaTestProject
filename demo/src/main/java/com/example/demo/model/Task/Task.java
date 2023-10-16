package com.example.demo.model.Task;

import com.example.demo.enums.TaskStatus;
import com.example.demo.model.Person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDate dueDate;

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private Set<Person> persons;

}
