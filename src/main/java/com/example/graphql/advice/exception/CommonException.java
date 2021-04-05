package com.example.graphql.advice.exception;


public class CommonException extends RuntimeException {
    // exception.yml의 메시지 값을 인자로 받아서 ExceptionAdivce에서 처리 함.

    public CommonException(String msg, Throwable t) {
        super(msg, t);
    }

    public CommonException(String msg) {
        super(msg);
    }
}