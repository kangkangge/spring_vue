package com.tbc.demo.catalog.Io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * IO关闭测试
 */
@Slf4j
public class IoTest {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\gekang\\Desktop\\154d8daac4a74773bccce9cf68c31380.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println("关闭 前" + fileInputStream.read());
        inputhTest(fileInputStream);
        System.out.println("关闭 后" + fileInputStream.read());

    }

    private static void inputhTest(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        System.out.println("方法内关闭 前" + read);
        inputStream.close();
        try {
            int read1 = inputStream.read();
            System.out.println("方法内关闭 后" + read1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}