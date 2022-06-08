package com.gh.tone.core.stream;

import java.util.Arrays;
import java.util.OptionalInt;

public class ReduceApp {

    public static void main(String[] args) {
        int[] ints = new int[]{4,5,3,9};
        // 最大值
        OptionalInt min = Arrays.stream(ints).reduce(Integer::max);
        System.out.println(min.getAsInt());
    }
}
