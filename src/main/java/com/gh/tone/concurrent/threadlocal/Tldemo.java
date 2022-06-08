package com.gh.tone.concurrent.threadlocal;

public class Tldemo {

    private static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        tl.set(1);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: %s",
            Thread.currentThread().getName(), tl.get()));
        fc();
        new Thread(Tldemo::fc).start();
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: %s",
            Thread.currentThread().getName(), tl.get()));
    }
}
