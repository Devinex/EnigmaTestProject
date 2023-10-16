package com.example.demo.controllers;

import com.example.demo.enums.TaskStatus;
import com.example.demo.model.Task.TaskCreationDto;
import com.example.demo.model.Task.TaskDto;
import com.example.demo.services.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getSingleTask(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }

    @PostMapping("/addNewTask")
    public ResponseEntity<TaskDto> createNewTask(@RequestBody TaskCreationDto taskCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addNewTask(taskCreationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskDto, id));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<TaskDto> changeStatus(@PathVariable Long id, @RequestParam TaskStatus taskStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.changeStatus(id, taskStatus));
    }

    @GetMapping("/getAllByTaskStatus")
    public ResponseEntity<List<TaskDto>> getAllByTaskStatus(@RequestParam TaskStatus taskStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllByTaskStatus(taskStatus));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskDto>> getAllByFilterType(@RequestParam String filterType, @RequestParam String searchValue) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllByFilterType(filterType, searchValue));
    }

    @GetMapping("/getAllTaskByDueDate")
    public ResponseEntity<List<TaskDto>> getAllTaskByDueDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate localDate) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getByDueDate(localDate));
    }

}
