package com.example.demo.mapper;

import com.example.demo.model.Person.Person;
import com.example.demo.model.Person.PersonCreationDto;
import com.example.demo.model.Person.PersonDto;

import java.util.ArrayList;
import java.util.List;


public class PersonMapper {
    public static Person fromCreationDto(PersonCreationDto personCreationDto) {
        Person person = new Person();
        person.setFirstName(personCreationDto.getFirstName());
        person.setLastName(personCreationDto.getLastName());
        person.setEmail(personCreationDto.getEmail());
        return person;
    }

    public static PersonDto fromPerson(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getTask()
        );
    }

    public static List<PersonDto> formPersonDtoListFromPersonList(List<Person> personList) {
        List<PersonDto> personDtos = new ArrayList<>();
        for (Person x : personList) {
            PersonDto personDto = new PersonDto(
                    x.getId(),
                    x.getFirstName(),
                    x.getLastName(),
                    x.getEmail(),
                    x.getTask()
            );
            personDtos.add(personDto);
        }
        return personDtos;
    }

    public static List<Person> formPersonListFromPersonDtoList(List<PersonDto> personDtoList) {
        List<Person> personList = new ArrayList<>();
        for (PersonDto x : personDtoList) {
            Person person = new Person(
                    x.getId(),
                    x.getFirstName(),
                    x.getLastName(),
                    x.getEmail(),
                    x.getTask()
            );
            personList.add(person);
        }
        return personList;
    }
}
