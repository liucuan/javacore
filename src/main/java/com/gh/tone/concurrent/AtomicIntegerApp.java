package com.gh.tone.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用10个线程打印0-10000, 最终得到结果10w.
 */
public class AtomicIntegerApp {
    static AtomicInteger i = new AtomicInteger();

    private static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int j = 0; j < 10; j++) {
            ts[j] = new Thread(new AddThread());
        }
        for (int j = 0; j < 10; j++) {
            ts[j].start();
        }
        for (int j = 0; j < 10; j++) {
            ts[j].join();
        }
        System.out.println(i);
    }
}
