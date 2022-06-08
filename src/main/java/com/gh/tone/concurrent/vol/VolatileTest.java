package com.gh.tone.concurrent.vol;

class VolatileTest {

    private static int INIT_VALUE = 0;
    private static final int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            //不从主内存读数据
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.println("the val update to " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"read").start();
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                // 写会写入到主内存
                System.out.println("update value to "+(++localValue));
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"write").start();
    }
}