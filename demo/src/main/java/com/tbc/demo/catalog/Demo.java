package com.tbc.demo.catalog;

import com.tbc.demo.common.model.User;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String str = "!2312534654---1***asdfasdf";
        System.out.println(str.replaceAll("[\\d\\D]", "*"));
    }
}
