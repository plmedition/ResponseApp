package com.backend.springboot.docker.person.repository;

import com.backend.springboot.docker.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);
}
