package com.example.graphql.advice.exception;

public class TokenException extends RuntimeException {

    public TokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException() {
        super();
    }
}