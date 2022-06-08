package com.gh.tone.core.lazy;

import java.util.function.Supplier;

public class Holder {

    private Supplier<Integer> integer = () -> createAndCacheInteger();

    public Holder() {
        System.out.println("Holder created");
    }

    public Integer getInteger() {
        // 第一次调用后heavy已经指向了新的instance，所以后续不再执行synchronized
        return integer.get();
    }

    private synchronized Integer createAndCacheInteger() {
        class IntegerFactory implements Supplier<Integer> {

            // 饥渴初始化
            private final Integer integerInstance = new Integer(0);

            @Override
            public Integer get() {
                // 每次返回固定的值
                return integerInstance;
            }
        }

        //第一次调用方法来会将heavy重定向到新的Supplier实例
        if (!IntegerFactory.class.isInstance(integer)) {
            integer = new IntegerFactory();
        }
        return integer.get();
    }

}
