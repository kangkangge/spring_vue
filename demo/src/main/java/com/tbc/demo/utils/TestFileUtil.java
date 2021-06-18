package com.tbc.demo.utils;

import java.io.File;

public class TestFileUtil {

    public static File getExcelFile() {
        File file = new File("标签导入模板.xls");
        return file;
    }

    public static void main(String[] args) {
        System.out.println(getExcelFile().getPath());
    }
}
