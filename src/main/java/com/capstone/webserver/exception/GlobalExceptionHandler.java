package com.capstone.webserver.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex) {

        log.error("Exception occured: {}", ex.getMessage());

        ExceptionCode exceptionCode = ex.getExceptionCode();
        return ExceptionResponse.toResponseEntity(exceptionCode);
    }
}
