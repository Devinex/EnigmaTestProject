package com.example.demo.model.Task;

import com.example.demo.enums.TaskStatus;
import com.example.demo.model.Person.Person;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDate dueDate;
    private Set<Person> persons;
}
