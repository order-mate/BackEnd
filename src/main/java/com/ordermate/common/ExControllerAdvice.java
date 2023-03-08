package com.ordermate.common;

import com.ordermate.common.exception.BaseException;
import com.ordermate.common.exception.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExControllerAdvice {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<?> handleBaseEx(BaseException exception){
		log.error("BaseException errorMessage(): {}",exception.getExceptionType().getErrorMessage());
		log.error("BaseException httpStatus(): {}",exception.getExceptionType().getHttpStatus());
		return new ResponseEntity<>(new ExceptionDto(
				exception.getExceptionType().getHttpStatus().value(),
				exception.getExceptionType().getHttpStatus(),
				exception.getExceptionType().getErrorMessage()
				),
				exception.getExceptionType().getHttpStatus());
	}
}
