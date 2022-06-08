package com.gh.tone.cache;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Caffeine[1]是一个高性能，高命中率，低内存占用，near optimal 的本地缓存，简单来说它是 Guava Cache 的优化加强版，有些文章把 Caffeine 称为“新一代的缓存”、“现代缓存之王”。
 */
public class CaffeineApp {
    private static LoadingCache<String, String> cache = Caffeine.newBuilder()
        .maximumSize(256)
        .initialCapacity(1)
        .expireAfterAccess(2, TimeUnit.DAYS)
        .expireAfterWrite(2,TimeUnit.HOURS)
        .refreshAfterWrite(1,TimeUnit.HOURS)
        .recordStats()
        .writer(new CacheWriter<String, String>() {
            @Override
            public void write(@NonNull String key, @NonNull String val) {
                System.out.println("key="+key+", CacheWriter write");
            }

            @Override
            public void delete(@NonNull String key, @Nullable String val,
                @NonNull RemovalCause removalCause) {
                System.out.println("key="+key+", cause="+removalCause+", CacheWriter delete");
            }
        }).build(new CacheLoader<String, String>() {
            @Override
            public @Nullable String load(@NonNull String key) throws Exception {
                return "value_" + key;
            }

            @Override
            public @Nullable String reload(@NonNull String key, @NonNull String oldValue)
                throws Exception {
                return "value_" + key;
            }
        });

    public static void main(String[] args) {
        int i = 0x45d9f3b;
        System.out.println(i);
    }

}
