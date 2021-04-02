package com.example.graphql.advice.exception;

public class CUserFailException extends RuntimeException {

    public CUserFailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserFailException(String msg) {
        super(msg);
    }

    public CUserFailException() {
        super();
    }
}