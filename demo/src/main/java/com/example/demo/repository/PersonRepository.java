package com.example.demo.repository;

import com.example.demo.model.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByEmail(String email);

    List<Person> findAllByTaskId(Long taskId);
}
