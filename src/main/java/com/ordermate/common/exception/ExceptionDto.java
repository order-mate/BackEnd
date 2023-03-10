package com.ordermate.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ExceptionDto {
    private LocalDateTime timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;

    public ExceptionDto(Integer status, HttpStatus error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
