package com.gh.tone.concurrent.threadlocal;

/**
 * InheritableThreadLocal 父线程传递给子线程
 */
public class ITLdemo {
    private static ThreadLocal<Integer> tl = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        tl.set(1);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: %s",
            Thread.currentThread().getName(), tl.get()));
        fc();
        new Thread(() -> {
            fc();
        }).start();
        Thread.sleep(1000L);
        fc();
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: %s",
            Thread.currentThread().getName(), tl.get()));
    }
}
