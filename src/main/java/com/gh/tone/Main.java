package com.gh.tone;

import java.util.*;

public class Main {
    static int len = 10;
    static List<String> list = new ArrayList<>(10);
    static String[] strings = {"A", "B", "C", "D"};
    static Set<List<String>> set = new HashSet<>(10 * 9 * 8 * 7);

    public static void main(String[] args) {
        repeatableArrangement(len, strings);
        set.stream().filter(l->{
            Set<String> t3 = new HashSet<>();
            t3.add(l.get(2));
            t3.add(l.get(5));
            t3.add(l.get(1));
            t3.add(l.get(4));
            if (t3.size()!=2) {
                return false;
            }
            return true;
        }).filter(l->{

            return true;
        })
                .forEach(System.out::println);

    }

    public static void repeatableArrangement(int k, String[] arr) {
        if (k == 1) {
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
//                System.out.print(list.toString() + ",");

                if (list.size() == len) {
                    List<String> t = new ArrayList<>(len);
                    t.addAll(list);
                    set.add(t);
                }
                list.remove(list.size() - 1); //移除尾部元素
            }
        } else if (k > 1) {
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
                repeatableArrangement(k - 1, arr); //不去重
                if (list.size() == len) {
                    List<String> t = new ArrayList<>(len);
                    t.addAll(list);
                    set.add(t);
                }
                list.remove(list.size() - 1); //移除尾部元素,不能用remove(Object),因为它会移除头部出现的元素，我们这里需要移除的是尾部元素
            }
        } else {
            return;
        }
    }

}
