package com.darahotel.darahotel.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateCodePayment {

    public String paymentCode() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
}
