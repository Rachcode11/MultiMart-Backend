package com.semicolon.africa.jumiaApp.utils;

import java.util.Random;

public class OTPGenerator {

    public static StringBuilder generateOtp(){
        String num = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            stringBuilder.append(num.charAt(random.nextInt(10)));
        }
        return stringBuilder;

    }

}
