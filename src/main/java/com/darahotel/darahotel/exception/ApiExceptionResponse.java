package com.darahotel.darahotel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ApiExceptionResponse extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;
}
