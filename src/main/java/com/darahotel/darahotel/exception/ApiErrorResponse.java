package com.darahotel.darahotel.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ApiErrorResponse {
    private String message;
    private HttpStatus status;
    private String datetime;

    public ApiErrorResponse() {
        setDateTime();
    }

    private void setDateTime() {
        // create an LocalDateTime object
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        setDatetime(now.format(format));
    }
}
