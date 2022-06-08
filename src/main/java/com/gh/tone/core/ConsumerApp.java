package com.gh.tone.core;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerApp {

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4), System.out::println);
    }
}
