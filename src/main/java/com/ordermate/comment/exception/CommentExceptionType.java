package com.ordermate.comment.exception;

import com.ordermate.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum CommentExceptionType implements BaseExceptionType {
    NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
    NO_AUTHORITY_COMMENT_REMOVE(HttpStatus.FORBIDDEN, "댓글을 삭제 할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    CommentExceptionType(HttpStatus httpStatus, String errorMessage) {
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
