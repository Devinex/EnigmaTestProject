package com.example.demo.services;

import com.example.demo.Exceptions.NotFound;
import com.example.demo.Exceptions.ExistException;
import com.example.demo.enums.SearchOperation;
import com.example.demo.enums.TaskStatus;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Person.Person;
import com.example.demo.model.Task.Task;
import com.example.demo.model.Task.TaskCreationDto;
import com.example.demo.model.Task.TaskDto;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.searchCriteria.SearchCriteria;
import com.example.demo.searchCriteria.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;

    public TaskDto addNewTask(TaskCreationDto taskCreationDto) {
        Optional<Task> byTitle = taskRepository.findByTitle(taskCreationDto.getTitle());
        if (byTitle.isPresent()) {
            throw new ExistException("Task with title: " + taskCreationDto.getTitle() + " already exist");
        }
        Task save = taskRepository.save(TaskMapper.fromCreationDto(taskCreationDto));
        return TaskMapper.fromTask(save);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        if (!taskList.isEmpty()) {
            for (Task x : taskList) {
                List<Person> personList = personRepository.findAllByTaskId(x.getId());
                if (personList.isEmpty())
                    continue;
                x.setPersons(new HashSet<>(personList));
            }

        }
        return TaskMapper.formTaskDtoListfromTaskList(taskList);
    }

    public TaskDto getTaskById(Long id) {
        Optional<Task> getById = taskRepository.findById(id);
        if (getById.isPresent()) {
            List<Person> personList = personRepository.findAllByTaskId(id);
            if (!personList.isEmpty())
                getById.get().setPersons(new HashSet<>(personList));
            return TaskMapper.fromTask(getById.get());
        }

        throw new NotFound("Task with id: " + id + "not found");
    }

    public void deleteTask(Long id) {
        Optional<Task> deleteById = taskRepository.findById(id);
        if (deleteById.isEmpty())
            throw new NotFound("Task with id: " + id + " not found");
        taskRepository.delete(deleteById.orElseThrow());
    }

    public TaskDto updateTask(TaskDto taskDto, Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setPersons(taskDto.getPersons());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setTitle(taskDto.getTitle());
        task.setTaskStatus(taskDto.getTaskStatus());
        taskRepository.save(task);
        return TaskMapper.fromTask(task);
    }

    public TaskDto changeStatus(Long id, TaskStatus taskStatus) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
        return TaskMapper.fromTask(task);
    }

    public List<TaskDto> getAllByTaskStatus(TaskStatus taskStatus) {
        List<Task> taskList = taskRepository.findByTaskStatus(taskStatus);
        if (taskList.isEmpty())
            throw new NotFound("Not found tasks with this status");
        return TaskMapper.formTaskDtoListfromTaskList(taskList);
    }

    public List<TaskDto> getAllByFilterType(String filterType, String searchValue) {
        TaskSpecification taskSpecification = new TaskSpecification();
        taskSpecification.add(new SearchCriteria(filterType, searchValue, SearchOperation.EQUAL));
        List<Task> taskList = taskRepository.findAll(Specification.where(taskSpecification));
        return TaskMapper.formTaskDtoListfromTaskList(taskList);
    }

    public List<TaskDto> getByDueDate(LocalDate date) {
        List<Task> taskList = taskRepository.findByDueDate(date);
        if (taskList.isEmpty())
            throw new NotFound("Not found BHtasks with this dueDate");
        return TaskMapper.formTaskDtoListfromTaskList(taskList);
    }
}



