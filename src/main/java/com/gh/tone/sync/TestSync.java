package com.gh.tone.sync;

public class TestSync implements Runnable {
    private int b = 100;

    public synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500);
        System.out.println("b=" + b);
    }

    public synchronized void m2() throws InterruptedException {
        Thread.sleep(250);
        b = 2000;
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync ts = new TestSync();
        Thread thread = new Thread(ts);
        thread.start();
        ts.m2();
        System.out.println("main thread b=" + ts.b);
    }
}
