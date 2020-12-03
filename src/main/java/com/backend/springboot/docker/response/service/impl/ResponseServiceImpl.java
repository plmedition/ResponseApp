package com.backend.springboot.docker.response.service.impl;

import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.exception.AppException;
import com.backend.springboot.docker.exception.FeedbackMessageEnum;
import com.backend.springboot.docker.mapper.DtoMapper;
import com.backend.springboot.docker.person.entity.Person;
import com.backend.springboot.docker.question.entity.Question;
import com.backend.springboot.docker.response.entity.Response;
import com.backend.springboot.docker.response.repository.ResponseRepository;
import com.backend.springboot.docker.response.service.ResponseService;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;
import com.backend.springboot.docker.validator.AppValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponseServiceImpl implements ResponseService {

    ResponseRepository responseRepository;
    AppValidator appValidator;
    DtoMapper dtoMapper;

    public ResponseServiceImpl(ResponseRepository responseRepository, AppValidator appValidator, DtoMapper dtoMapper) {
        this.responseRepository = responseRepository;
        this.appValidator = appValidator;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public ResponseDto addResponse(ResponseRequestParams responseRequestParams) throws AppException {

        appValidator.validateAddResponseRequestParams(responseRequestParams);
        appValidator.validateExistingPerson(responseRequestParams.getPersonId());
        appValidator.validateExistingQuestion(responseRequestParams.getQuestionId());
        appValidator.validateNotExistingResponse
                (responseRequestParams.getPersonId(), responseRequestParams.getQuestionId());

        Response responseToAdd = buildResponse(responseRequestParams);

        responseRepository.save(responseToAdd);
        responseToAdd = responseRepository.findById(responseToAdd.getId()).get();

        return dtoMapper.mapToResponseDto(responseToAdd);
    }

    @Override
    public ResponseDto updateResponse(ResponseRequestParams responseRequestParams) throws AppException {

        appValidator.validateUpdateResponseRequestParams(responseRequestParams);
        appValidator.validateExistingPerson(responseRequestParams.getPersonId());
        appValidator.validateExistingQuestion(responseRequestParams.getQuestionId());
        appValidator.validateExistingResponse
                (responseRequestParams.getPersonId(), responseRequestParams.getQuestionId());
        Optional<Response> optionalResponseRetrievedFromDB = responseRepository.findById(responseRequestParams.getResponseId());
        if (optionalResponseRetrievedFromDB.isPresent()) {
            Response responseRetrievedFromDB = optionalResponseRetrievedFromDB.get();
            responseRetrievedFromDB.setResponse(responseRequestParams.getResponse());
            responseRetrievedFromDB.setComments(responseRequestParams.getComments());
            responseRetrievedFromDB.setPerson(new Person(responseRequestParams.getPersonId()));
            responseRetrievedFromDB.setQuestion(new Question(responseRequestParams.getQuestionId()));
            responseRepository.save(responseRetrievedFromDB);

            responseRetrievedFromDB = responseRepository.findById(responseRetrievedFromDB.getId()).get();
            return dtoMapper.mapToResponseDto(responseRetrievedFromDB);

        } else {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_NOT_EXISTS);
        }

    }

    @Override
    public ResponseDto getResponse(ResponseRequestParams responseRequestParams) throws AppException {

        appValidator.validateGetResponseRequestParams(responseRequestParams);
        appValidator.validateExistingPerson(responseRequestParams.getPersonId());
        appValidator.validateExistingQuestion(responseRequestParams.getQuestionId());
        Response response = responseRepository.findByPerson_PersonIdAndQuestion_QuestionId(responseRequestParams.getPersonId(), responseRequestParams.getQuestionId());
        if (response != null) {
            return dtoMapper.mapToResponseDto(response);
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST.toString(), FeedbackMessageEnum.RESPONSE_NOT_EXISTS);
        }

    }

    @Override
    public List<PersonDto> getPersonDtoListByQuestion(ResponseRequestParams responseRequestParams) throws AppException {

        appValidator.validateGetPersonDtoListByQuestion(responseRequestParams);
        appValidator.validateExistingQuestion(responseRequestParams.getQuestionId());
        List<Response> responseList = responseRepository.findByQuestion_QuestionId(responseRequestParams.getQuestionId());
        return dtoMapper.mapToPersonDtoList(responseList);

    }

    @Override
    public List<ResponseDto> getResponsesByQuestionAndDate(ResponseRequestParams responseRequestParams) throws AppException {

        appValidator.validateGetResponsesByQuestion(responseRequestParams);
        appValidator.validateExistingQuestion(responseRequestParams.getQuestionId());
        List<Response> responseList = responseRepository.findByQuestion_QuestionId(responseRequestParams.getQuestionId());
        LocalDate startDate = responseRequestParams.getStartDate();
        LocalDate endDate = responseRequestParams.getEndDate();

        List<Response> responseListFiltered = responseList.stream().filter(r -> r.getPerson().getEntryDate().isBefore(endDate)
                && r.getPerson().getEntryDate().isAfter(startDate)).collect(Collectors.toList());

        return dtoMapper.mapToResponseDtoList(responseListFiltered);

    }

    private Response buildResponse(ResponseRequestParams responseRequestParams) {

        Response responseToAdd = new Response();

        Person personToAdd = new Person();
        personToAdd.setPersonId(responseRequestParams.getPersonId());
        responseToAdd.setPerson(personToAdd);

        Question questionToAdd = new Question();
        questionToAdd.setQuestionId(responseRequestParams.getQuestionId());
        responseToAdd.setQuestion(questionToAdd);

        responseToAdd.setResponse(responseRequestParams.getResponse());
        responseToAdd.setComments(responseRequestParams.getComments());

        return responseToAdd;
    }
}
