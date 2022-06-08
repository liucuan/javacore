package com.gh.tone.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionApp {
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result= new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> sizeList = map(Arrays.asList("abc","1234"),s -> s.length());
        System.out.println(sizeList);
        //复合
         Function<Integer, Integer> f = x -> x + 1;
         Function<Integer, Integer> g = x -> x * 2;
         Function<Integer, Integer> h = f.andThen(g);
         int r = h.apply(2);
        System.out.println(r);

    }
}
