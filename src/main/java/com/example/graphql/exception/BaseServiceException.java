package com.example.graphql.exception;

import com.example.graphql.result.Result;

public class BaseServiceException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BaseServiceException(Result result) {
        super(result);
    }

}

