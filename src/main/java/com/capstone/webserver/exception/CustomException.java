package com.capstone.webserver.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionCode exceptionCode;
    private final Throwable cause;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());

        this.exceptionCode = exceptionCode;
        this.cause = null;
    }

    public CustomException(ExceptionCode exceptionCode, Throwable cause) {
        super(exceptionCode.getMessage(), cause);
        this.exceptionCode = exceptionCode;
        this.cause = cause;
    }
}
