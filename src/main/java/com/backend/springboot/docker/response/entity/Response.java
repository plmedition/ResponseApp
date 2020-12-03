package com.backend.springboot.docker.response.entity;

import com.backend.springboot.docker.person.entity.Person;
import com.backend.springboot.docker.question.entity.Question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Response implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "xid_question")
    private Question question;


    @ManyToOne
    @JoinColumn(name = "xid_person")
    private Person person;

    private String response;
    private String comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
