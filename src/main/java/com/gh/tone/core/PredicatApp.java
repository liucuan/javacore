package com.gh.tone.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicatApp {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("a");
        listOfStrings.add("");
        listOfStrings.add(" ");
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println(nonEmpty);

    }
}
