package com.example.demo.mapper;

import com.example.demo.model.Task.Task;
import com.example.demo.model.Task.TaskCreationDto;
import com.example.demo.model.Task.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    public static Task fromCreationDto(TaskCreationDto taskCreationDto) {
        Task task = new Task();
        task.setDescription(taskCreationDto.getDescription());
        task.setDueDate(taskCreationDto.getDueDate());
        task.setTaskStatus(taskCreationDto.getTaskStatus());
        task.setTitle(taskCreationDto.getTitle());
        task.setPersons(taskCreationDto.getPersons());
        return task;
    }

    public static TaskDto fromTask(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTaskStatus(),
                task.getDueDate(),
                task.getPersons()
        );
    }

    public static Task fromTaskDto(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getTaskStatus(),
                taskDto.getDueDate(),
                taskDto.getPersons()

        );
    }

    public static List<TaskDto> formTaskDtoListfromTaskList(List<Task> taskList) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task x : taskList) {
            TaskDto taskDto = new TaskDto(
                    x.getId(),
                    x.getTitle(),
                    x.getDescription(),
                    x.getTaskStatus(),
                    x.getDueDate(),
                    x.getPersons()
            );
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }
}
