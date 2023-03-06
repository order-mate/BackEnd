package com.ordermate.post.exception;

import com.ordermate.common.exception.BaseException;
import com.ordermate.common.exception.BaseExceptionType;

public class PostException extends BaseException {
    private final PostExceptionType postExceptionType;

    public PostException(PostExceptionType postExceptionType) {
        this.postExceptionType = postExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.postExceptionType;
    }
}
