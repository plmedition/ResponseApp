package com.backend.springboot.docker.exception;

public class AppException extends Throwable{

    private String code;
    private FeedbackMessageEnum feedbackMessageEnum;

    public AppException(String code, FeedbackMessageEnum feedbackMessageEnum){
        this.code = code;
        this.feedbackMessageEnum = feedbackMessageEnum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FeedbackMessageEnum getFeedbackMessageEnum() {
        return feedbackMessageEnum;
    }

    public void setFeedbackMessageEnum(FeedbackMessageEnum feedbackMessageEnum) {
        this.feedbackMessageEnum = feedbackMessageEnum;
    }
}
