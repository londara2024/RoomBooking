package com.darahotel.darahotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ApiExceptionResponse.class)
    public ResponseEntity<?> notFoundException(ApiExceptionResponse e) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(e.getHttpStatus());
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage(e.getMessage() + " [+] Sorry, It has something wrong. Please contact to the administrator");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(response);
    }

}
