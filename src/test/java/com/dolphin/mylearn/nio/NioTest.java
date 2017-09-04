package com.dolphin.mylearn.nio;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jichuan.wang on 2017/9/4.
 */
public class NioTest {
    @Test
    public void test01() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_READ);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){

            }
            Set selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    PrintUtil.print("准备就绪啦");
                }else if(selectionKey.isReadable()){
                    PrintUtil.print("现在可读啦");
                }else if (selectionKey.isWritable()){
                    PrintUtil.print("现在可以写入啦");
                }
                iterator.remove();
            }
        }

    }
}
