package com.backend.springboot.docker.response.service;

import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.exception.AppException;
import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.response.entity.Response;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;

import java.util.List;

public interface ResponseService {

    /** 5.-
     * Add a new response to a question for a certain person.
     * If the response exists already an
     * exception will be thrown.
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    ResponseDto addResponse(ResponseRequestParams responseRequestParams) throws AppException;

    /** 6.-
     * Update an existing response to a question for a certain person. If the response exists then it will
     * be modified
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    ResponseDto updateResponse(ResponseRequestParams responseRequestParams) throws AppException;

    /** 7.-
     * Get a {@link List} of {@link PersonDto} with all Persons and its Responses based on a certain question.
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    List<PersonDto> getPersonDtoListByQuestion(ResponseRequestParams responseRequestParams) throws AppException;

    /** 8.-
     * Get a {@link List} of {@link Response} by the provided questionId and filter by {@link ResponseRequestParams}
     * startDate and endDate
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    List<ResponseDto> getResponsesByQuestionAndDate(ResponseRequestParams responseRequestParams) throws AppException;

    /**
     * Get a certain response based on a certain PersonId and QuestionId
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    ResponseDto getResponse(ResponseRequestParams responseRequestParams) throws AppException;
}
