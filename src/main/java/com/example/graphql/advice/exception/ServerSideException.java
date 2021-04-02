package com.example.graphql.advice.exception;

import com.example.graphql.model.response.code.ErrorCode;

public class ServerSideException extends BusinessException{

    public ServerSideException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ServerSideException(ErrorCode errorCode) {
        super(errorCode);
    }
}
