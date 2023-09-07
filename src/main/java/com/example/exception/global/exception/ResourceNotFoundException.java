package com.example.exception.global.exception;

import com.example.exception.global.ExceptionCode;
import jakarta.persistence.EntityNotFoundException;


public class ResourceNotFoundException extends EntityNotFoundException {

    private final ExceptionCode exceptionCode;

    public ResourceNotFoundException(
            ExceptionCode exceptionCode
    ) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
