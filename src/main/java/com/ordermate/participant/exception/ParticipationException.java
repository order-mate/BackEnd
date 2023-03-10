package com.ordermate.participant.exception;

import com.ordermate.common.exception.BaseException;
import com.ordermate.common.exception.BaseExceptionType;
import com.ordermate.member.exception.MemberExceptionType;

public class ParticipationException extends BaseException {
    private final ParticipationExceptionType participationExceptionType;

    public ParticipationException(ParticipationExceptionType participationExceptionType) {
        this.participationExceptionType = participationExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.participationExceptionType;
    }
}

