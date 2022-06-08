package com.gh.tone.concurrent.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TransmittableThreadLocal 在使用线程池等会池化复用线程的执行组件情况下，<br></> 提供ThreadLocal值的传递功能，解决异步执行时上下文传递的问题。<br></>
 * 一个Java标准库本应为框架/中间件设施开发提供的标配能力 主要提供了：TTL值的抓取、回放和恢复方法（即CRR操作）
 */
public class TTLdemo2 {

    private static ExecutorService es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS,
        new ArrayBlockingQueue<>(100), x -> new Thread(x));
    private static TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal();

    public static void main(String[] args) {
        es.submit(() -> System.out.println("线程池只有真正submit的时候才初始化线程池（懒加载），所以这里先初始化线程池"));
        Thread.yield();
        ttl.set("value-set-in-parent-1");
        Runnable task = () -> System.out.println("子线程拿到的值：" + ttl.get());
        Runnable ttlRunnable = TtlRunnable.get(task);
        es.submit(ttlRunnable);
        Thread.yield();
    }


}
