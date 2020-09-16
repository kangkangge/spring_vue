package com.tbc.demo.utils;

import java.util.Random;


public class RandomUtil {
    private static String[] randomNum = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public RandomUtil() {
    }

    public static String randomNumString(int length) {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            String s = randomNum[rd.nextInt(10)];
            sb.append(s);
        }

        return sb.toString();
    }
}


