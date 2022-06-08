package com.gh.tone.hcpd.c2;

/**
 * 2.5
 */
public class DaemonDemo {
    public static class DaemonThread extends Thread {

        @Override
        public void run() {
            while (true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonThread();
        t.setDaemon(true);
        t.start();
        Thread.sleep(2000L);
    }
}
