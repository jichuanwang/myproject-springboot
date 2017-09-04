package com.dolphin.mylearn;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

import javax.servlet.Servlet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jichuan.wang on 2017/9/4.
 */
public class SocketTest {
    @Test
    public  void server(){
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            PrintUtil.print("服务开始监听8081端口........");
            while (true){
                Socket socket = serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String content = null;
                while ((content = bufferedReader.readLine()) != null){
                    PrintUtil.print(content);
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedWriter.write("hello client");
                bufferedWriter.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test01(){
        try {
            Socket socket = new Socket("localhost",8082);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("hello socket");
            bufferedWriter.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content = null;
            while ((content = bufferedReader.readLine()) != null){
                PrintUtil.print(content);
            }
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
