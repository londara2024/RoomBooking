package com.darahotel.darahotel.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ValidateObj {
    public static Boolean validateString(String str) {
        if (str == null || str.isEmpty() || str.isBlank()) { return false; }
        return true;
    }

    public static Boolean validateBigDecimal(BigDecimal num) {
        if (num == null || num.doubleValue() == 0.0) { return false; }
        return true;
    }

    public static Boolean validateInt(Integer num) {
        if (num == null || num == 0) { return false; }
        return true;
    }

    public static Boolean validateLocalDateTime (LocalDateTime date) {
        if ( date == null ) { return false; }
        return true;
    }
}
