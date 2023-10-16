package com.example.demo.repository;

import com.example.demo.enums.TaskStatus;
import com.example.demo.model.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    Optional<Task> findByTitle(String title);

    List<Task> findByDueDate(LocalDate date);

    List<Task> findByTaskStatus(TaskStatus taskStatus);
}
