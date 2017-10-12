package com.dolphin.mylearn.blockingqueue;

import com.dolphin.mylearn.springboot.util.PrintUtil;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jichuan.wang on 2017/10/11.
 */
public class BlockQueueTest {
    public static void main(String[] args){
        PrintUtil.print("hello");
        BlockingQueue blockingQueue = new ArrayBlockingQueue(50);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            Producer producer = new Producer(blockingQueue,i);
            executorService.submit(producer);
        }
        Consumer consumer = new Consumer(blockingQueue);
        executorService.submit(consumer);

    }
    static class Producer implements Runnable{
        private BlockingQueue queue;
        private int id;
        public Producer(BlockingQueue queue,int id){
            this.queue = queue;
            this.id = id;
        }
        @Override
        public void run() {
            Random r = new Random();
            PrintUtil.print("工人"+id+"开始工作.....");
            try {
                for(int i=0;i<100;i++){
                    Thread.sleep(r.nextInt(1000)+0L);
                    PrintUtil.print("工人"+id+"生产第"+i+"个馒头");
                    queue.put(id+"_"+i);
                }
            }catch (Exception e){

            }

        }
    }

    static class Consumer implements Runnable{
        private BlockingQueue queue;
        public Consumer(BlockingQueue blockingQueue){
            this.queue = blockingQueue;
        }
        @Override
        public void run() {
            Random r = new Random();
            PrintUtil.print("吃客开始吃啦");
            try {
                while (true){
                    Thread.sleep(r.nextInt(100));
                    String result = (String)queue.take();
                    PrintUtil.print("吃客吃掉"+result);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
