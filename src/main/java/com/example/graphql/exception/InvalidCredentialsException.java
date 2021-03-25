package com.example.graphql.exception;

public class InvalidCredentialsException extends  Exception {

    public InvalidCredentialsException(String message){
        super(message);
    }
}