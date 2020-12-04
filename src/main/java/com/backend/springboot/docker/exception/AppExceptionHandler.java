package com.backend.springboot.docker.exception;

import com.backend.springboot.docker.builder.AppBuilder;
import com.backend.springboot.docker.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private AppBuilder appBuilder;

    public AppExceptionHandler(AppBuilder appBuilder) {
        this.appBuilder = appBuilder;
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleException(AppException appException) {
        return appBuilder.buildErrorDto(appException.getFeedbackMessageEnum().getValue());
    }
}
