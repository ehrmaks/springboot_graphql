package com.example.graphql.advice.exception;

public class SignUpDupException extends RuntimeException {

    public SignUpDupException(String msg, Throwable t) {
        super(msg, t);
    }

    public SignUpDupException(String msg) {
        super(msg);
    }

    public SignUpDupException() {
        super();
    }
}