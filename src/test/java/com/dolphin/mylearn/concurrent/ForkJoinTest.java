package com.dolphin.mylearn.concurrent;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by jichuan.wang on 2017/8/30.
 */
public class ForkJoinTest {
    public static class ForkTask extends RecursiveTask<Long>{
        private long THREAD_HOLD=1000;
        private long start;
        private long end;
        public ForkTask(long start,long end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            long sum = 0;
            boolean canCompute = (end-start) < THREAD_HOLD;
            if(canCompute){
                for(long i=start;i<end;i++){
                    sum+=i;
                }
            }else {
                long step = (end - start)/100;
                ArrayList<ForkTask> subTasks = new ArrayList<ForkTask>();
                long pos = start;
                for(int i=0;i<100;i++){
                    long lastOne = pos+step;
                    if(lastOne>end)lastOne = end;
                    ForkTask forkTask = new ForkTask(pos,lastOne);
                    pos+=step+1;
                    subTasks.add(forkTask);
                    forkTask.fork();
                }
                for(ForkTask forkTask:subTasks){
                    sum+=forkTask.join();
                }
            }
            return sum;
        }
    }
}
