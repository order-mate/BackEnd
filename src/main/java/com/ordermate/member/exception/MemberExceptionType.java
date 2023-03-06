package com.ordermate.member.exception;

import com.ordermate.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {
    NOT_FOUND(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다"),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT,"이미 존재하는 아이디입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    MemberExceptionType(HttpStatus httpStatus, String errorMessage) {
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
