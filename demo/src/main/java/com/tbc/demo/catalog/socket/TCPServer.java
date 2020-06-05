package com.tbc.demo.catalog.socket;


/**
 * 服务器的实现步骤：
 *      1.创建服务器Serversocket对象和系统要指定的端口号
 *      2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket
 *      3.使用Socket对象中的方法get Input stream（）获取网络字节输入流Input stream对象
 *      4.使用网络字节输入流Inputstream对象中的方法read，读取客户端发送的数据
 *      5.使用Socket对象中的方法getoutput stream（）获取网络字节输出流Outputstream对象
 *      6.使用网络字节输出流Outputstream对象中的方法write，给客户端回写数据
 *      7.释放资源（Socket，ServerSocket）
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class TCPServer {

    public static void main(String[] args) throws IOException {
        //1.创建服务器Serversocket对象和系统要指定的端口号
        ServerSocket server = new ServerSocket(8888);
        //2.使用ServerSocket对象中的方法accept，获取到请求的客户端对象
        Socket socket = server.accept();
        //3.使用Socket对象中的方法getInput stream（）获取网络字节输入流Inputstream对象
        InputStream is = socket.getInputStream();
        //4.使用网络字节输入流Inp中tstream对象中的方法read，读取客户端发送的数据
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println("运行了:  "+new String(bytes,0,len));
        //5.使用Socket对象中的方法getoutputStream（）获取网络字节输出流Outputstream对象
        OutputStream outputStream = socket.getOutputStream();
        //6.使用网络字节输出流outputstream对象中的方法write，给客户端回写数据
        outputStream.write(" ".getBytes());
        //7.释放资源（Socket，ServerSocket）
        socket.close();
        server.close();

    }
}
