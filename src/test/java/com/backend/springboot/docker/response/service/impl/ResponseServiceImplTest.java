package com.backend.springboot.docker.response.service.impl;

import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.exception.AppException;
import com.backend.springboot.docker.exception.FeedbackMessageEnum;
import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.response.entity.Response;
import com.backend.springboot.docker.response.service.ResponseService;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration()
@SpringBootTest

class ResponseServiceImplTest {


    private static final Long PERSON_ID_1_EMMA = 1L;
    private static final Long PERSON_ID_2_NOAH = 2L;
    private static final Long PERSON_ID_3_OLIVIA = 3L;
    private static final Long PERSON_ID_4_LIAM = 4L;
    private static final Long PERSON_ID_5_AVA = 5L;
    private static final Long PERSON_ID_6_WILLIAM = 6L;
    private static final Long PERSON_ID_7_SOPHIA = 7L;
    private static final Long PERSON_ID_NON_EXISTING = 1000L;

    private static final String PERSON_NAME_OLIVIA = "Olivia";
    private static final String PERSON_NAME_LIAM = "Liam";
    private static final String PERSON_NAME_WILLIAM = "William";
    private static final String PERSON_NAME_NOAH = "Noah";
    private static final String PERSON_NAME_EMMA = "Emma";

    private static final Long QUESTION_ID_1_WHATS_YOUR_NAME = 1L;
    private static final Long QUESTION_ID_2_HOW_OLD_ARE_YOU = 2L;
    private static final Long QUESTION_ID_3_WHERE_ARE_YOU_FROM = 3L;
    private static final Long QUESTION_ID_4_WHATS_YOUR_CURRENT_JOB_POSITION = 4L;
    private static final Long QUESTION_ID_5_NON_EXISTING_QUESTION = 5L;

    private static final String QUESTION_WHATS_YOUR_NAME = "What’s your name?";
    private static final String QUESTION_HOW_OLD_ARE_YOU = "How old are you?";
    private static final String QUESTION_WHERE_ARE_YOU_FROM = "Where are you from?";
    private static final String QUESTION_WHATS_YOUR_CURRENT_JOB_POSITION = "What’s your current job position?";

    @Autowired
    ResponseService responseService;

    @Test
    void testAddResponseOK() throws AppException {
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_6_WILLIAM); // William
        responseRequestParams.setQuestionId(QUESTION_ID_2_HOW_OLD_ARE_YOU); // How old are you?
        responseRequestParams.setResponse("34");
        ResponseDto responseDto = responseService.addResponse(responseRequestParams);
        assertNotNull(responseDto, "Element was not inserted.");
        assertEquals(PERSON_NAME_WILLIAM, responseDto.getPersonName());
        assertEquals(QUESTION_HOW_OLD_ARE_YOU, responseDto.getQuestion());
        assertEquals("34", responseDto.getResponse());
    }

    @Test
    void testAddResponseOK_withCommentsInclude() throws AppException {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_6_WILLIAM); // William
        responseRequestParams.setQuestionId(QUESTION_ID_3_WHERE_ARE_YOU_FROM); // How old are you?
        responseRequestParams.setResponse("Berlin");
        responseRequestParams.setComments("Here comes the comment.");
        ResponseDto responseDto = responseService.addResponse(responseRequestParams);
        assertNotNull(responseDto, "Element was not inserted.");
        assertEquals(PERSON_NAME_WILLIAM, responseDto.getPersonName());
        assertEquals(QUESTION_WHERE_ARE_YOU_FROM, responseDto.getQuestion());
        assertEquals("Berlin", responseDto.getResponse());
        assertNotNull(responseDto.getComments(), "Here comes the comment.");

    }

    @Test
    void testAddResponseKO_addResponseToAnExistingOne() {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_4_LIAM); // Liam
        responseRequestParams.setQuestionId(QUESTION_ID_2_HOW_OLD_ARE_YOU); //"How old are you?"
        responseRequestParams.setResponse("60");
        try {
            ResponseDto responseDto = responseService.addResponse(responseRequestParams);
            assertNotNull(responseDto, "Element was inserted.");
        } catch (AppException e) {
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.RESPONSE_EXISTS_ALREADY);
        }

    }

    @Test
    void testAddResponseKO_AddResponseToNonExistingPerson() {
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_NON_EXISTING);
        responseRequestParams.setQuestionId(QUESTION_ID_1_WHATS_YOUR_NAME);
        responseRequestParams.setResponse("DUNNO");
        try {
            ResponseDto responseDto = responseService.addResponse(responseRequestParams);
            assertNotNull(responseDto, "Element was inserted.");
        } catch (AppException e) {
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.PERSON_NOT_EXISTS);
        }
    }

    @Test
    void testAddResponseKO_AddResponseWithNoResponsePresent() {
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_5_AVA);
        responseRequestParams.setQuestionId(QUESTION_ID_1_WHATS_YOUR_NAME);
        try {
            ResponseDto responseDto = responseService.addResponse(responseRequestParams);
            assertNull(responseDto, "Element was inserted.");
        } catch (AppException e) {
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.RESPONSE_NULL);
        }
    }

    // Test getResponse
    @Test
    void testGetResponseOK() throws AppException {
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_2_NOAH);
        responseRequestParams.setQuestionId(QUESTION_ID_2_HOW_OLD_ARE_YOU);
        ResponseDto responseDto = responseService.getResponse(responseRequestParams);
        assertEquals(PERSON_NAME_NOAH, responseDto.getPersonName());
        assertEquals(QUESTION_HOW_OLD_ARE_YOU, responseDto.getQuestion());
        assertEquals("32", responseDto.getResponse());
        assertNotNull(responseDto, "Element was not present.");
    }

    @Test
    void testGetResponseKO_withNonExistingResponse() throws AppException {
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_7_SOPHIA);
        responseRequestParams.setQuestionId(QUESTION_ID_1_WHATS_YOUR_NAME);
        try {
            responseService.getResponse(responseRequestParams);
        } catch (AppException e) {
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.RESPONSE_NOT_EXISTS);
        }
    }

    // TEST UpdateResponse

    @Test
    void testUpdateResponseOK() throws AppException {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_1_EMMA);
        responseRequestParams.setQuestionId(QUESTION_ID_4_WHATS_YOUR_CURRENT_JOB_POSITION);
        ResponseDto responseDto = responseService.getResponse(responseRequestParams);

        responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_1_EMMA);
        responseRequestParams.setQuestionId(QUESTION_ID_4_WHATS_YOUR_CURRENT_JOB_POSITION);
        responseRequestParams.setResponseId(responseDto.getResponseId());
        responseRequestParams.setResponse("QA Engineer");
        responseDto = responseService.updateResponse(responseRequestParams);
        assertNotNull(responseDto, "Element was not updated");
        assertEquals(PERSON_NAME_EMMA, responseDto.getPersonName());
        assertEquals(QUESTION_WHATS_YOUR_CURRENT_JOB_POSITION, responseDto.getQuestion());
        assertEquals("QA Engineer", responseDto.getResponse());

    }

    @Test
    void testUpdateResponseKO_withNonExistingResponse() {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setPersonId(PERSON_ID_1_EMMA);
        responseRequestParams.setQuestionId(QUESTION_ID_4_WHATS_YOUR_CURRENT_JOB_POSITION);
        responseRequestParams.setResponseId(1000L); // This response does not exist.
        responseRequestParams.setResponse("QA Engineer");
        try {
            responseService.updateResponse(responseRequestParams);
        } catch (AppException e) {
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.RESPONSE_NOT_EXISTS);
        }
    }

    //TEST getPersonDtoListByQuestion
    @Test
    void testGetPersonDtoListByQuestionOK() throws AppException {

        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setQuestionId(QUESTION_ID_1_WHATS_YOUR_NAME);
        List<PersonDto> personDtoList = responseService.getPersonDtoListByQuestion(responseRequestParams);
        assertNotNull(personDtoList, "No List<PersonDto> was retrieved");
        assertEquals(4, personDtoList.size(), "Different number of PersonDto was retrieved");

    }

   @Test
    void testGetPersonDtoListByQuestionIdKO_notExistingQuestion(){
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setQuestionId(QUESTION_ID_5_NON_EXISTING_QUESTION);
        try {
            List<PersonDto> personDtoList = responseService.getPersonDtoListByQuestion(responseRequestParams);
            assertNull(personDtoList, "PersonDtoList cannot be retrieved");
        }catch(AppException e){
            assertEquals(e.getFeedbackMessageEnum(), FeedbackMessageEnum.QUESTION_NOT_EXISTS);
        }
    }

    @Test
    void testGetResponsesByQuestionOK()throws AppException{

        //Only responses from OLIVIA and LIAM are expected
        ResponseRequestParams responseRequestParams = new ResponseRequestParams();
        responseRequestParams.setQuestionId(QUESTION_ID_4_WHATS_YOUR_CURRENT_JOB_POSITION);
        responseRequestParams.setStartDate(LocalDate.of(2019, 01, 01));
        responseRequestParams.setEndDate(LocalDate.of(2020, 01, 01));

        List<ResponseDto> responseDtoList = responseService.getResponsesByQuestionAndDate(responseRequestParams);
        assertNotNull(responseDtoList , "Response is empty.");
        assertEquals(2, responseDtoList.size(), "Only two responses are expected.");
        assertEquals(PERSON_NAME_OLIVIA, responseDtoList.get(0).getPersonName());
        assertEquals("Senior Developer", responseDtoList.get(0).getResponse());
        assertEquals(PERSON_NAME_LIAM, responseDtoList.get(1).getPersonName());
        assertEquals("Master of Universe", responseDtoList.get(1).getResponse());
    }
}