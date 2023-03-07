package com.ordermate.participant.exception;

import com.ordermate.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ParticipationExceptionType implements BaseExceptionType {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Host가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    ParticipationExceptionType(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
