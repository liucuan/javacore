package com.gh.tone.concurrent.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TransmittableThreadLocal 在使用线程池等会池化复用线程的执行组件情况下，<br></> 提供ThreadLocal值的传递功能，解决异步执行时上下文传递的问题。<br></>
 * 一个Java标准库本应为框架/中间件设施开发提供的标配能力
 * 主要提供了：TTL值的抓取、回放和恢复方法（即CRR操作）
 */
public class TTLdemo {

    private static ExecutorService es = TtlExecutors.getTtlExecutorService(
        Executors.newFixedThreadPool(2));
    private static ThreadLocal tl = new TransmittableThreadLocal();

    public static void main(String[] args) {
        new Thread(() -> {
            String mainThreadName = "main_01";
            tl.set(1);
            es.execute(() -> {
                sleep(1L);

                System.out.println(String
                    .format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });
            es.execute(() -> {

                sleep(1L);

                System.out.println(String
                    .format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {

                sleep(1L);

                System.out.println(String
                    .format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });

            sleep(1L); //确保上面的会在tl.set执行之前执行

            tl.set(2); // 等上面的线程池第一次启用完了，父线程再给自己赋值

            es.execute(() -> {
                sleep(1L);
                System.out.println(String
                    .format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String
                    .format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String
                    .format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName,
                        Thread.currentThread().getName(), tl.get()));
            });

            System.out.println(
                String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));

        }).start();
        new Thread(() -> {

            String mainThreadName = "main_02";

            tl.set(3);

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(3), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(3), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(3), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            sleep(1L); //确保上面的会在tl.set执行之前执行
            tl.set(4); // 等上面的线程池第一次启用完了，父线程再给自己赋值

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(4), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(4), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            es.execute(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(4), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), tl.get()));
            });

            System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));

        }).start();
    }

    private static void sleep(Long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
