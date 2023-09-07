package com.example.exception.member;

import com.example.exception.global.ExceptionCode;
import com.example.exception.global.exception.ResourceNotFoundException;

import static com.example.exception.member.MemberException.MEMBER_NOT_FOUND;

public class LoginMemberException extends ResourceNotFoundException {

    private final ExceptionCode exceptionCode;

    public LoginMemberException() {
        super(MEMBER_NOT_FOUND);
        this.exceptionCode = MEMBER_NOT_FOUND;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
