package com.capstone.webserver.common.advice;

import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.common.response.exception.ExceptionCode;
import com.capstone.webserver.common.response.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Profile("dev")
public class GlobalExceptionHandlerDev extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomExceptionDev(CustomException ex) {

        log.error("Exception occured: {}", ex.getMessage(), ex);

        ExceptionCode exceptionCode = ex.getExceptionCode();
        return ExceptionResponse.toResponseEntity(exceptionCode);
    }
}
