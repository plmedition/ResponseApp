package com.backend.springboot.docker.dto;

import com.backend.springboot.docker.person.entity.Person;

import java.time.LocalDate;

/**
 * Dto to retrieve information regarding {@link Person} through
 * the {@link com.backend.springboot.docker.controller.ResponseController}
 */

public class PersonDto extends Person {

    public PersonDto(Long id, String name, LocalDate entryDate, String response){
        this.personId = id;
        this.name = name;
        this.entryDate = entryDate;
        this.response = response;
    }

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
