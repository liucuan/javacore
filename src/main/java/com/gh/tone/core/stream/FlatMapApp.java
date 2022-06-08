package com.gh.tone.core.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapApp {

    public static void main(String[] args) {
        String[] strs = new String[]{"Hello", "world"};
        Stream<String> stringStream = Arrays.stream(strs);
        List<String> s = stringStream.map(w -> w.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());
        System.out.println(s);

        //给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，
        // 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> arr1 = Arrays.asList(1, 2, 3);
        List<Integer> arr2 = Arrays.asList(3, 4);
        List<int[]> intsList = arr1.stream().flatMap(i -> arr2.stream().map(j -> new int[]{i, j}))
            .collect(Collectors.toList());
        intsList.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
