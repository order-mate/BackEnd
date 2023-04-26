package com.ordermate.participant.exception;

import com.ordermate.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ParticipationExceptionType implements BaseExceptionType {
    HOST_NOT_FOUND(HttpStatus.NOT_FOUND, "Host가 존재하지 않습니다."),
    PARTICIPATION_NOT_FOUND(HttpStatus.NOT_FOUND, "이 멤버는 이 방 참여자 리스트에 존재하지 않습니다."),
    ALREADY_PARTICIPATED_THIS_POST(HttpStatus.BAD_REQUEST, "이미 이 방에 참여되어 있습니다.");

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
