package com.ordermate.member.exception;

import com.ordermate.common.exception.BaseException;
import com.ordermate.common.exception.BaseExceptionType;

public class MemberException extends BaseException {
    private final MemberExceptionType memberExceptionType;

    public MemberException(MemberExceptionType memberExceptionType) {
        this.memberExceptionType = memberExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.memberExceptionType;
    }
}
