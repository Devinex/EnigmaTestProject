package com.example.demo.model.Task;

import com.example.demo.enums.TaskStatus;
import com.example.demo.model.Person.Person;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value
public class TaskCreationDto {
    String title;
    String description;
    TaskStatus taskStatus;
    LocalDate dueDate;
    Set<Person> persons;
}
