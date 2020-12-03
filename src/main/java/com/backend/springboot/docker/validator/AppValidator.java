package com.backend.springboot.docker.validator;

import com.backend.springboot.docker.exception.AppException;
import com.backend.springboot.docker.exception.FeedbackMessageEnum;
import com.backend.springboot.docker.person.repository.PersonRepository;
import com.backend.springboot.docker.question.repository.QuestionRepository;
import com.backend.springboot.docker.response.repository.ResponseRepository;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AppValidator {

    private PersonRepository personRepository;
    private QuestionRepository questionRepository;
    private ResponseRepository responseRepository;

    public AppValidator(PersonRepository personRepository, QuestionRepository questionRepository, ResponseRepository responseRepository) {
        this.personRepository = personRepository;
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    public boolean validateAddResponseRequestParams(ResponseRequestParams responseRequestParams) throws AppException {
        validateResponseRequestParams(responseRequestParams);
        validatePersonId(responseRequestParams);
        validateQuestionId(responseRequestParams);
        validateResponse(responseRequestParams);
        return true;
    }

    public boolean validateUpdateResponseRequestParams(ResponseRequestParams responseRequestParams) throws AppException {
        validateAddResponseRequestParams(responseRequestParams);
        validateResponseId(responseRequestParams);
        return true;
    }

    public boolean validateGetResponseRequestParams(ResponseRequestParams responseRequestParams) throws AppException{
        validateResponseRequestParams(responseRequestParams);
        validatePersonId(responseRequestParams);
        validateQuestionId(responseRequestParams);
        return true;
    }

    public boolean validateGetPersonDtoListByQuestion(ResponseRequestParams responseRequestParams)throws AppException{
        validateResponseRequestParams(responseRequestParams);
        validateQuestionId(responseRequestParams);
        return true;
    }

    public boolean validateGetResponsesByQuestion(ResponseRequestParams responseRequestParams) throws AppException{

        validateResponseRequestParams(responseRequestParams);
        validateQuestionId(responseRequestParams);
        validateStartDate(responseRequestParams);
        validateEndDate(responseRequestParams);
        return true;

    }

    private boolean validateResponseRequestParams(ResponseRequestParams responseRequestParams) throws AppException{

        if (responseRequestParams == null) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.ADD_RESPONSE_REQUEST_PARAMS_NULL);
        }
        return true;

    }

    private boolean validatePersonId(ResponseRequestParams responseRequestParams)throws AppException{

        if (responseRequestParams.getPersonId() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.PERSON_ID_NULL);
        }
        return true;

    }

    private boolean validateQuestionId(ResponseRequestParams responseRequestParams) throws AppException{

        if (responseRequestParams.getQuestionId() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.QUESTION_ID_NULL);
        }
        return true;

    }

    private boolean validateResponse(ResponseRequestParams responseRequestParams) throws AppException {

        if (responseRequestParams.getResponse() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_NULL);
        }
        return true;

    }

    private boolean validateResponseId(ResponseRequestParams responseRequestParams)throws AppException{

        if(responseRequestParams.getResponseId() == null){
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_ID_NULL);
        }
        return true;

    }

    private boolean validateStartDate(ResponseRequestParams responseRequestParams) throws AppException{

        if(responseRequestParams.getStartDate() == null){
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.START_DATE_NULL);
        }
        return true;

    }

    private boolean validateEndDate(ResponseRequestParams responseRequestParams) throws AppException{

        if(responseRequestParams.getEndDate() == null){
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.END_DATE_NULL);
        }
        return true;

    }

    public boolean validateExistingPerson(Long personId) throws AppException {

        if (!personRepository.existsById(personId)) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.PERSON_NOT_EXISTS);
        }
        return true;

    }

    public boolean validateExistingQuestion(Long questionId) throws AppException {

        if (!questionRepository.existsById(questionId)) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.QUESTION_NOT_EXISTS);
        }
        return true;

    }

    public boolean validateNotExistingResponse(Long personId, Long questionId) throws AppException {

        if (responseRepository.existsByPerson_PersonIdAndQuestion_QuestionId(personId, questionId)) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_EXISTS_ALREADY);
        }
        return true;

    }

    public boolean validateExistingResponse(Long personId, Long questionId) throws AppException {

        if (!responseRepository.existsByPerson_PersonIdAndQuestion_QuestionId(personId, questionId)) {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_NOT_EXISTS);
        }
        return true;

    }
}
