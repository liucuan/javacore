package com.gh.tone.hcpd.c2;

/**
 * 2.3
 */
public class NoVisibility {

    // 若没有volatile修饰 主线程则永远看不到 ready的变化
    private static volatile boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000L);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
