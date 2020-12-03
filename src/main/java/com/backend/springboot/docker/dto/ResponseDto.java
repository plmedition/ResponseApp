package com.backend.springboot.docker.dto;

public class ResponseDto {
    private Long responseId;
    private String question;
    private String personName;
    private String response;
    private String comments;

    public ResponseDto(Long responseId, String question, String personName, String response, String comments) {
        this.responseId = responseId;
        this.question = question;
        this.personName = personName;
        this.response = response;
        this.comments = comments;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
}
