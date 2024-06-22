package com.darahotel.darahotel.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class DateValidation {

    public static Integer calculateDate (LocalDateTime startDate, LocalDateTime endDate) {

        String sDate = String.valueOf(startDate).substring(0,10);
        String eDate = String.valueOf(endDate).substring(0,10);
        log.info("Start Date: {}", sDate);
        log.info("End Date: {}", eDate);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(sDate, dateFormatter);
        LocalDate end = LocalDate.parse(eDate, dateFormatter);

        return Math.toIntExact(ChronoUnit.DAYS.between(start, end));
    }

    public static String getCreateNow() {
        // Formatting a date-time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        log.info("Formatted date-time: {}" , formattedDateTime);
        return formattedDateTime;
    }



}
