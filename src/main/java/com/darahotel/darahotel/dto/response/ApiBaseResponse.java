package com.darahotel.darahotel.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class ApiBaseResponse <T>{
    private T data;
    private String message;
    private HttpStatus status;
    private String datetime;

    public ApiBaseResponse() {
        setDateTime();
    }

    private void setDateTime() {
        // create an LocalDateTime object
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        setDatetime(now.format(format));
    }
}
