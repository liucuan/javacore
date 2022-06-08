package com.gh.tone.concurrent.vol;

class VolatileTest2 {

    private static int INIT_VALUE = 0;
    private static final int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            // 读不会从主存从去拿数据
            while (localValue < MAX_LIMIT) {
                System.out.println("t1 update value to " + (++localValue));
                INIT_VALUE = localValue;
            }
        }, "t1").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                // 写会写入到主存
                System.out.println("t2 update value to " + (++localValue));
                INIT_VALUE = localValue;
            }
        }, "t2").start();
    }
}