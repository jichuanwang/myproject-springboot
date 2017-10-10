package com.dolphin.mylearn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by jichuan.wang on 2017/9/5.
 */
public class NioTcpClient {
    private InetSocketAddress inetSocketAddress;

    public NioTcpClient(String hostname, int port) {
        inetSocketAddress = new InetSocketAddress(hostname, port);
    }
    public void send(String requestData) {
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            socketChannel.write(ByteBuffer.wrap(requestData.getBytes()));
            while (true) {
                byteBuffer.clear();
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    socketChannel.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String hostname = "localhost";
        String requestData = "Actions speak louder than words!";
        int port = 8080;
        new NioTcpClient(hostname, port).send(requestData);
    }
}
