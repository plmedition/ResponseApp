package com.backend.springboot.docker.builder;

import com.backend.springboot.docker.dto.ErrorDto;
import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.response.entity.Response;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppBuilder {

    public ResponseEntity<ResponseDto> buildResponse(ResponseDto responseDto) {
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public ResponseEntity<List<PersonDto>> buildListPersonDtoResponse(List<PersonDto> personDtoList) {
        return new ResponseEntity<>(personDtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseDto>> buildListResponse(List<ResponseDto> responseDtoList) {
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    public ResponseRequestParams buildResponseRequestParams(Long responseId, ResponseRequestParams responseRequestParams) {
        return buildResponseRequestParams(responseRequestParams.getPersonId(),
                responseRequestParams.getQuestionId(),
                responseId,
                responseRequestParams.getResponse(),
                responseRequestParams.getComments(),
                responseRequestParams.getStartDate(),
                responseRequestParams.getEndDate());
    }

    public ResponseRequestParams buildResponseRequestParams(Long personId, Long questionId, Long responseId, String response, String comments, LocalDate startDate, LocalDate endDate) {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(personId);
        responseRequestParams.setQuestionId(questionId);
        responseRequestParams.setResponseId(responseId);
        responseRequestParams.setResponse(response);
        responseRequestParams.setComments(comments);
        responseRequestParams.setStartDate(startDate);
        responseRequestParams.setEndDate(endDate);
        return responseRequestParams;

    }

    public ResponseEntity<ErrorDto> buildErrorDto(String message) {

        ErrorDto errorDTO = new ErrorDto(message);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }

}
