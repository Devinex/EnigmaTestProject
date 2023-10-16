package com.example.demo.model.Person;

import com.example.demo.model.Task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min (value = 3)
    @Max( value = 30)
    private String firstName;
    @Min(2)
    @Max(50)
    private String lastName;
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @Nullable
    @JsonIgnore
    private Task task;

}
