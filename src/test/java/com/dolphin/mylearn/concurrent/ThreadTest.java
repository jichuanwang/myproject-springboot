package com.dolphin.mylearn.concurrent;

import com.dolphin.mylearn.springboot.util.PrintUtil;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jichuan.wang on 2017/8/30.
 */
public class ThreadTest {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static class CountThread extends Thread {
        @Override
        public void run() {
            try {
                PrintUtil.print(Thread.currentThread().getId() + "-----" + System.currentTimeMillis());
                sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

    }

    public static void main(String[] args) {

        Thread t1 = new CountThread();
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        PrintUtil.print(r.toString() + "   废弃了");
                    }
                });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(t1);
        }

    }
}
