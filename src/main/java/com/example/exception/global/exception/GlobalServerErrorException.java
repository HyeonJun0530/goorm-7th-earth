package com.example.exception.global.exception;


import com.example.exception.global.ExceptionCode;

import static com.example.exception.global.GlobalErrorCode.SERVER_ERROR;

public class GlobalServerErrorException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public GlobalServerErrorException() {

        super(SERVER_ERROR.getMessage());
        this.exceptionCode = SERVER_ERROR;
    }

    public ExceptionCode getExceptionCode() {

        return exceptionCode;
    }
}
