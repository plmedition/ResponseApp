package com.backend.springboot.docker.controller;

import com.backend.springboot.docker.builder.AppBuilder;
import com.backend.springboot.docker.dto.PersonDto;
import com.backend.springboot.docker.dto.ResponseDto;
import com.backend.springboot.docker.exception.AppException;
import com.backend.springboot.docker.response.service.ResponseService;
import com.backend.springboot.docker.response.service.params.request.ResponseRequestParams;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ResponseController {

    private ResponseService responseService;
    private AppBuilder appBuilder;

    public ResponseController(ResponseService responseService, AppBuilder appBuilder) {

        this.responseService = responseService;
        this.appBuilder = appBuilder;

    }

    /**
     * 5. Create a REST service to add new RESPONSE into database.
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    @PostMapping("/response")
    public ResponseEntity<ResponseDto> addResponse(@RequestBody ResponseRequestParams responseRequestParams) throws AppException {

        ResponseDto responseDto = responseService.addResponse(responseRequestParams);
        return appBuilder.buildResponse(responseDto);

    }

    /**
     * 6. Create a REST service to update a RESPONSE from database.
     *
     * @param responseRequestParams
     * @return
     * @throws AppException
     */
    @PutMapping("/response/{responseId}")
    public ResponseEntity<ResponseDto> updateResponse(@PathVariable Long responseId, @RequestBody ResponseRequestParams responseRequestParams) throws AppException {
        ResponseRequestParams responseRequestParamsWithResponseId = appBuilder.buildResponseRequestParams(responseId, responseRequestParams);
        ResponseDto responseDto = responseService.updateResponse(responseRequestParamsWithResponseId);
        return appBuilder.buildResponse(responseDto);

    }

    /**
     * 7. Create a REST service to show PERSONs with related RESPONSE based on a QUESTION input.
     *
     * @param questionId
     * @return
     * @throws AppException
     */
    @GetMapping("/personsByQuestion")
    public ResponseEntity<List<PersonDto>> getPersonDtoListByQuestion(@RequestParam Long questionId) throws AppException {

        ResponseRequestParams responseRequestParams = appBuilder.buildResponseRequestParams(null, questionId, null, null, null, null, null);
        List<PersonDto> personDtoList = responseService.getPersonDtoListByQuestion(responseRequestParams);
        return appBuilder.buildListPersonDtoResponse(personDtoList);

    }

    /**
     * 8. Create a REST service to show all RESPONSE given a QUESTION and ENTRY_DATE filter
     *
     * @param questionId
     * @param startDate
     * @param endDate
     * @return
     * @throws AppException
     */
    @GetMapping(value = "/responsesByQuestionAndDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseDto>> getResponsesByQuestionAndDate(@RequestParam Long questionId,
                                                                           @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate startDate,
                                                                           @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate endDate) throws AppException {

        ResponseRequestParams responseRequestParams = appBuilder.buildResponseRequestParams(null, questionId, null, null, null, startDate, endDate);
        List<ResponseDto> responseDtoList = responseService.getResponsesByQuestionAndDate(responseRequestParams);
        return appBuilder.buildListResponse(responseDtoList);

    }


}
