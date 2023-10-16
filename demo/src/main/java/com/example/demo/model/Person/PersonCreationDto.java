package com.example.demo.model.Person;

import lombok.Value;

@Value
public class PersonCreationDto {
    String firstName;
    String lastName;
    String email;
}
