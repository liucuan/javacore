package com.gh.tone.hcpd.c2;

/**
 * java高并发程序设计第二版
 * 2.2.6
 */
public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread {

        @Override
        public void run() {
            for (i = 0; i < 10000000; i++) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        //主程序会等待add线程执行完才结束 否则会输出0或很小的数字
        addThread.join();
        System.out.println(i);
    }
}
