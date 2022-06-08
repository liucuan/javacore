package com.gh.tone.core.lazy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class MemoizeSupplier<T> implements Supplier<T> {
    final Supplier<T> delegate;
    ConcurrentMap<Class<?>, T> map = new ConcurrentHashMap<>(1);

    public MemoizeSupplier(Supplier<T> delegate) {
        this.delegate = delegate;
    }
    @Override
    public T get() {
        return this.map.computeIfAbsent(MemoizeSupplier.class,
            k -> this.delegate.get());
    }
    public static <T> Supplier<T> of(Supplier<T> provider) {
        return new MemoizeSupplier<>(provider);
    }
}
