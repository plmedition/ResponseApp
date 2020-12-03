package com.backend.springboot.docker.dto;

/**
 * DTO managed by {@link com.backend.springboot.docker.builder.AppBuilder} to provide feedback in case
 * a validation error happens and  a {@link com.backend.springboot.docker.exception.AppException} is thrown and catched
 * by {@link com.backend.springboot.docker.exception.AppExceptionHandler}
 */
public class ErrorDto {

    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
