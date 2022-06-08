package com.gh.tone.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceApp {
    static AtomicReference<String> ar = new AtomicReference<>("abc");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ar.compareAndSet("abc", "def")) {
                    System.out.println("Thread:" + Thread.currentThread().getId() + " change value to " + ar.get());
                } else {
                    System.out.println("Thread:" + Thread.currentThread().getId() + " change failed! " + ar.get());
                }

            }).start();
        }
    }
}
