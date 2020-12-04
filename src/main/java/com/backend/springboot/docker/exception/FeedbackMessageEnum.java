package com.backend.springboot.docker.exception;

/**
 * Enum used to provided feedback in case of a {@link AppException} is thrown
 */
public enum FeedbackMessageEnum {
    ADD_RESPONSE_REQUEST_PARAMS_NULL("addResponseRequestParams.must.be.not.null"),
    PERSON_ID_NULL("person.id.name.must.be.not.null"),
    QUESTION_ID_NULL("question.id.must.be.not.null"),
    RESPONSE_ID_NULL("response.id.must.be.not.null"),
    RESPONSE_NULL("response.must.be.not.null"),
    PERSON_NOT_EXISTS("person.does.not.exist"),
    QUESTION_NOT_EXISTS("question.does.not.exist"),
    RESPONSE_NOT_EXISTS("response.does.not.exists"),
    RESPONSE_EXISTS_ALREADY("response.exists.already"),
    START_DATE_NULL("start.date.must.be.not.null"),
    END_DATE_NULL("end.date.must.be.not.null");

    private String value;

    FeedbackMessageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
