package com.gh.tone.core.stream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class MenuApp {

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
        List<String> names = menu.stream().filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .skip(2)
            .limit(3)
            .collect(Collectors.toList());
        System.out.println(names);
        //卡路里大于300的菜名长度
        List<Integer> nameLens = menu.stream().filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println(nameLens);

        //统计菜的数量
        Optional<Integer> dishNum = menu.stream().map(d -> 1).reduce(Integer::sum);
        System.out.println("dishNum=" + dishNum.get());
        //根据卡路里分类
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
            .collect(groupingBy(dish -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
            }));
        System.out.println(dishesByCaloricLevel);

        //根据类别进行卡路里分类
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
            .collect(
                groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }))
            );
        System.out.println(dishesByTypeCaloricLevel);

        //热量最高的菜
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
            .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);
        Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
            .collect(groupingBy(Dish::getType,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                    Optional::get)));
        System.out.println(mostCaloricByType2);
        //菜肴热量总和
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
            .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        //菜肴卡路里分组
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
            .collect(groupingBy(Dish::getType, mapping(dish -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
            }, toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType);
        //菜单按照素食和非素食分开
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        //菜单按照素食和非素食 最高卡路里
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(
                maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }
}