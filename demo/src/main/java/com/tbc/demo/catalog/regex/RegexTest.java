package com.tbc.demo.catalog.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexTest {

    String str = "user_Id";

    @Test
    public void test() {
        boolean matches = Pattern.matches("user[_]?id", str);
        Pattern compile = Pattern.compile("user[_]?id$");
        System.out.println(compile.matcher(str.toLowerCase()).find());
    }
}
