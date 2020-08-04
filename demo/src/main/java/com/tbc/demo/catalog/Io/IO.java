package com.tbc.demo.catalog.Io;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IO {

    public static void main(String[] args) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("99", 12312);
        map.put("991", 12312);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        String parse = JSONObject.toJSON(map).toString();
        Map<String, Object> parse1 = (Map<String, Object>) JSONObject.parse(parse);
        System.out.println(parse1);

    }

    /**
     * 文件常用API
     *
     * @param path
     * @return
     */
    public static File fileTest(String path) {
        //public File(String pathname) ：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
        //public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
        //public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例
        File file = new File(path);
        //public String getAbsolutePath() ：返回此File的绝对路径名字符串。
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        //public String getPath() ：将此File转换为路径名字符串。
        String parent = file.getParent();
        System.out.println(parent);
        //public String getName() ：返回由此File表示的文件或目录的名称。
        String name = file.getName();
        System.out.println(name);
        //public long length() ：返回由此File表示的文件的长度。
        long length = file.length();
        System.out.println(length);
        return file;
    }

    /**
     * public FileOutputStream(File file) ：创建文件输出流以写入由指定的 File对象表示的文件。
     * public FileOutputStream(String name) ： 创建文件输出流以指定的名称写入文件。
     *
     * @param file 向传入的文件内写出字节;
     */
    public static void fileOutputStreamTest(String name, File file) throws IOException {
        FileOutputStream fileOutputStream;
        if (StringUtils.isNotEmpty(name)) {
            //fileOutputStream = new FileOutputStream(ne
            //
            // w File(name),true);
            fileOutputStream = new FileOutputStream(new File(name), true);
        } else {
            fileOutputStream = new FileOutputStream(file);
        }
        //9,2 是偏移量
        fileOutputStream.write("HelloWorld".getBytes(), 9, 2);
        fileOutputStream.write("HelloWorld".getBytes());
        fileOutputStream.close();
    }

    /**
     * 使用IO序列化
     *
     * @param object
     * @return
     */
    private static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] getByte = byteArrayOutputStream.toByteArray();
            return getByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param binaryByte
     * @return
     */
    private static Object unserizlize(byte[] binaryByte) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        byteArrayInputStream = new ByteArrayInputStream(binaryByte);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object obj = objectInputStream.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出文件流工具
     *
     * 核心 api:
     *      InputStream # read([]Byte b) : 从输入流读取一些字节数，并将它们存储到缓冲区b 。 读取长度最大为b的length 如果输入流,读取完毕返回 -1;
     *      OutputStream # write (byte[] b,int off, int len)  :  从指定的字节数组写入len字节，从偏移off开始输出到此输出流。
     *                  b - 数据。
     *                  off - 数据中的起始偏移量。
     *                  len - 要写入的字节数。
     *      OutputStream # close()
     *      InputStream # close()  关闭流,使用后一定要关闭io流,注意捕捉异常确保两个流都能成功关闭!
     * @param is
     * @param os
     * @throws Exception
     */
    public static void fileUpload(InputStream is, OutputStream os) throws Exception {
        try {
            //定义一个变量记录读取到
            int length = 0;
            byte[] b = new byte[1024 * 1024 * 10];
            while (true) {
                length = is.read(b);
                //读取完毕退出
                if (length < 0)
                    break;
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            try {
                is.close();
            } catch (Exception e1) {
                os.close();
            }
        }



    }
}
