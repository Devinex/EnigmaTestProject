package com.example.demo.services;

import com.example.demo.Exceptions.ExistException;
import com.example.demo.Exceptions.NotFound;
import com.example.demo.enums.SearchOperation;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person.Person;
import com.example.demo.model.Person.PersonCreationDto;
import com.example.demo.model.Person.PersonDto;
import com.example.demo.model.Task.Task;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.searchCriteria.PersonSpecification;
import com.example.demo.searchCriteria.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final TaskRepository taskRepository;

    public PersonDto addNewPerson(PersonCreationDto personCreationDto) {
        Optional<Person> byEmail = personRepository.findByEmail(personCreationDto.getEmail());
        if (byEmail.isPresent()) {
            throw new ExistException("User with email: " + personCreationDto.getEmail() + " already exist");
        }
        if (!isValidEmailAddress(personCreationDto.getEmail()))
            throw new ExistException("Bad email address");
        Person save = personRepository.save(PersonMapper.fromCreationDto(personCreationDto));
        return PersonMapper.fromPerson(save);
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void deletePerson(Long id) {
        Optional<Person> deletebyId = personRepository.findById(id);
        if (deletebyId.isEmpty())
            throw new NotFound("User with " + id + " not found");
        personRepository.delete(deletebyId.orElseThrow());
    }

    public List<PersonDto> getAllPersons() {
        List<Person> personList = personRepository.findAll();
        return PersonMapper.formPersonDtoListFromPersonList(personList);
    }

    public PersonDto getPersonById(Long id) {
        Optional<Person> getById = personRepository.findById(id);
        if (getById.isEmpty())
            throw new NotFound("User with " + id + " not found");
        return PersonMapper.fromPerson(getById.get());
    }

    public List<PersonDto> getAllByFilterType(String filterType, String searchValue) {
        PersonSpecification personSpecification = new PersonSpecification();
        personSpecification.add(new SearchCriteria(filterType, searchValue, SearchOperation.EQUAL));
        List<Person> personList = personRepository.findAll(Specification.where(personSpecification));
        return PersonMapper.formPersonDtoListFromPersonList(personList);
    }

    public PersonDto assignTask(Long taskId, Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty())
            throw new NotFound("Not found user with id: " + personId);
        Person personLoaded = person.get();
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty())
            throw new NotFound("Not found task with id: " + taskId);
        Task taskLoaded = task.get();
        personLoaded.setTask(taskLoaded);
        personRepository.save(personLoaded);
        return PersonMapper.fromPerson(personLoaded);
    }

    public void deleteAssignTask(Long taskId, Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty())
            throw new NotFound("Not found user with id: " + personId);
        Person personLoaded = person.get();
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty())
            throw new NotFound("Not found task with id: " + taskId);
        personLoaded.setTask(null);
        personRepository.save(personLoaded);
    }
}

