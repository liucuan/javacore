package com.gh.tone.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapApp {
    public static void main(String[] args) {
        linkedHashMap();
        treeMap();
    }

    public static void linkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("4", "4");
        map.put("3", "3");
        System.out.println(map);
    }

    public static void treeMap() {
        Map<String, String> map = new TreeMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("4", "4");
        map.put("3", "3");
        System.out.println(map);
    }
}
