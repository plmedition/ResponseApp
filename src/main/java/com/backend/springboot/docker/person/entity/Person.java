package com.backend.springboot.docker.person.entity;

import com.backend.springboot.docker.response.entity.Response;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "person")
@Entity
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long personId;
    protected String name;

    @Column(name = "entry_date")
    protected LocalDate entryDate;

    @OneToMany(mappedBy = "person")
    private Set<Response> responses;

    public Person() {
    }

    public Person(Long personId) {
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}
