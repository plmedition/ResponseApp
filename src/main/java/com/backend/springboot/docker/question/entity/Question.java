package com.backend.springboot.docker.question.entity;

import com.backend.springboot.docker.response.entity.Response;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long questionId;
    private String question;

    @OneToMany(mappedBy = "question")
    private Set<Response> responses;

    public Question() {
    }

    public Question(Long questionId){
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long id) {
        this.questionId = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}
