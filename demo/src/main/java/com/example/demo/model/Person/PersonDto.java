package com.example.demo.model.Person;

import com.example.demo.model.Task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Task task;

}
