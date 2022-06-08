package com.gh.tone.hcpd.c3;

import java.util.concurrent.*;

/**
 * 3.2.4
 */
public class RejcetThreadPoolDemo {

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(
                System.currentTimeMillis() + "Thread Id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10),
            Executors.defaultThreadFactory(), (Runnable r, ThreadPoolExecutor executor) ->
            System.out.println(r.toString() + " is discard"));
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            es.submit(task);
            Thread.sleep(10L);
        }
    }
}
