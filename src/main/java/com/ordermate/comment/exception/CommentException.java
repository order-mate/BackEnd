package com.ordermate.comment.exception;

import com.ordermate.common.exception.BaseException;
import com.ordermate.common.exception.BaseExceptionType;

public class CommentException extends BaseException {
    private final CommentExceptionType commentExceptionType;

    public CommentException(CommentExceptionType commentExceptionType) {
        this.commentExceptionType = commentExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return commentExceptionType;
    }
}
