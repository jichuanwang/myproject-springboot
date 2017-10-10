package com.dolphin.mylearn.nio;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by jichuan.wang on 2017/9/4.
 */
public class NioTest {
    @Test
    public void test01() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        PrintUtil.print("socket server started.......");
        while (true){
            int n = selector.select();
            if(n == 0){
                continue;
            }
            Set selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    PrintUtil.print("准备就绪啦");
                    ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel channel = server.accept();
                    if(channel != null){
                        channel.configureBlocking(false);
                        channel.register(selector,SelectionKey.OP_READ);

                    }
                }else if(selectionKey.isReadable()){
                    PrintUtil.print("现在可读啦");
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int size = 0;
                    while (true){
                        size = channel.read(byteBuffer);
                        if(size>0 ){
                            String receive = Charset.forName("utf8").newDecoder()
                                    .decode(byteBuffer).toString();
                            String[] messages = receive.split("\r\n");
                            for(String s : messages){
                                PrintUtil.print(s);
                            }
//                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            break;
                        }
                    }
                    channel.close();

                   /* SocketChannel sc = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffers = (ByteBuffer)selectionKey.attachment();
                    buffers.clear();
                    if(sc.read(buffers) == -1){
                        sc.close();
                    }else {
                        buffers.flip();
                        String receive = Charset.forName("utf8").newDecoder()
                                .decode(buffers).toString();
                        String[] messages = receive.split("\r\n");
                        for(String s : messages){
                            PrintUtil.print(s);
                        }
                    }*/
                }else if (selectionKey.isWritable()){
                    PrintUtil.print("现在可以写入啦");
                }
                iterator.remove();
            }
        }

    }
    @Test
    public void testSys(){
       String a = "01234567";
       char b = a.charAt(1);
       PrintUtil.print(b);
       PrintUtil.print(b== 1);
    }
}
