package com.gh.tone.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal 父线程传递给子线程 传递的是引用  遇到线程池就无用
 */
public class ITLdemo3 {

    private static ExecutorService es = Executors.newSingleThreadExecutor();
    private static ThreadLocal<Integer> tl = new InheritableThreadLocal<>();
    private static ThreadLocal<Integer> tl2 = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        tl.set(1);
        System.out
            .println(String.format("线程名称-%s, 变量值=%s,变量值2=%s", Thread.currentThread().getName(), tl.get(), tl2.get()));
        es.submit(() -> System.out
            .println(String.format("线程名称-%s, 变量值=%s,变量值2=%s", Thread.currentThread().getName(), tl.get(), tl2.get())));
        //第一次父线程未传进的值，再也传不到子线程了
        tl2.set(2);
        es.execute(() -> {
            System.out.println(
                String.format("线程名称-%s, 变量值=%s,变量值2=%s", Thread.currentThread().getName(), tl.get(), tl2.get()));
        });

        System.out
            .println(String.format("线程名称-%s, 变量值=%s,变量值2=%s", Thread.currentThread().getName(), tl.get(), tl2.get()));
    }


}
