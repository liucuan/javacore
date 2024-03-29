package com.gh.tone.hcpd.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 3.2.6 ThreadPoolExecutor提供了beforeExecute(),afterExecute()和terminated()三个接口用来对线程池控制
 */
public class ExtThreadPool {

    public static class MyTask implements Runnable {

        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(
                "正在执行" + ":Thread Id:" + Thread.currentThread().getId() + ",Task Name=" + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完：" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程退出");
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("Task-" + i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
