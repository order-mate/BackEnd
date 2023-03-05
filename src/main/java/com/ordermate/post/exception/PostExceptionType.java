package com.ordermate.post.exception;

import com.ordermate.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum PostExceptionType implements BaseExceptionType {
    NOT_FOUND(HttpStatus.NOT_FOUND, "포스트가 존재하지 않습니다"),
    NO_AUTHORITY_POST_TOGGLE(HttpStatus.FORBIDDEN, "호스트가 아니면 방 상태를 변경 할 수 없음")
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    PostExceptionType(HttpStatus httpStatus, String errorMessage) {
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
