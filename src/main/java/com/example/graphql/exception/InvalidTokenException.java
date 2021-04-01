package com.example.graphql.exception;

import com.example.graphql.result.code.ErrorCode;

public class InvalidTokenException extends BusinessException{

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}

