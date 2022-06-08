package com.gh.tone.vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class TupleDemo {

    public static void main(String[] args) {
        Tuple2<String, Integer> java8 = Tuple.of("java", 8);
        System.out.println(java8._1());
        System.out.println(java8._2());
        //映射元组组件
        final Tuple2<String, Integer> that = java8.map(s -> s.substring(2), i -> i + 3);
        System.out.println("that=" + that);
        //也可以使用一个映射函数来映射一个元组。
        final Tuple2<String, Integer> that2 = java8.map((s, i) -> Tuple.of(s, i + 3));
        System.out.println("that2=" + that2);
    }
}
