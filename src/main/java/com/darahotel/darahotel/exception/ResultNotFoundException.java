package com.darahotel.darahotel.exception;

import org.springframework.http.HttpStatus;

public class ResultNotFoundException extends ApiExceptionResponse{
    public ResultNotFoundException(String resourceName, String type) {
        super(String.format("%s with id = %s not found!", resourceName, type), HttpStatus.NOT_FOUND);
    }
}
