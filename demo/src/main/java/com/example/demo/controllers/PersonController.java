package com.example.demo.controllers;

import com.example.demo.Exceptions.ExistException;
import com.example.demo.Exceptions.NotFound;
import com.example.demo.model.Person.PersonCreationDto;
import com.example.demo.model.Person.PersonDto;
import com.example.demo.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDto>> getPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonDto>> getAllByFilterType(@RequestParam String filterType, @RequestParam String searchValue) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllByFilterType(filterType, searchValue));
    }

    @PostMapping("/addPerson")
    public ResponseEntity<PersonDto> createNewPerson(@RequestBody PersonCreationDto personCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addNewPerson(personCreationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getSinglePerson(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(id));
    }

    @PutMapping("/assignTask/{id}")
    public ResponseEntity<PersonDto> assignTask(@PathVariable Long id, @RequestParam Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.assignTask(id, personId));
    }

    @PutMapping("/deleteAssignTask/{id}")
    public ResponseEntity deleteAssignTask(@PathVariable Long id, @RequestParam Long personId) {
        personService.deleteAssignTask(id, personId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted assign Task from Person");
    }

    @ExceptionHandler({ExistException.class})
    public ResponseEntity personExistExceptionHandler(ExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity personNotFoundExceptionHandler(NotFound ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
