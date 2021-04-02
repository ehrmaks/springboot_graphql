package com.example.graphql.advice.exception;

import com.example.graphql.model.response.code.ErrorCode;

public class InvalidAuthorityException extends BusinessException{

    public InvalidAuthorityException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidAuthorityException(ErrorCode errorCode) {
        super(errorCode);
    }
}

