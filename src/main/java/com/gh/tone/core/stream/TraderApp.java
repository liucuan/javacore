package com.gh.tone.core.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TraderApp {

    //(1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
    //(2) 交易员都在哪些不同的城市工作过?
    //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
    //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
    //(5) 有没有交易员是在米兰工作的?
    //(6) 打印生活在剑桥的交易员的所有交易额。
    //(7) 所有交易中，最高的交易额是多少?
    //(8) 找到交易额最小的交易。
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
        //(1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
        final List<Transaction> sorted = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(Comparator.comparingInt(Transaction::getValue))
            .collect(Collectors.toList());
        System.out.println("sorted=" + sorted);
        //(2) 交易员都在哪些不同的城市工作过?
        final List<String> disCity = transactions.stream()
            .map(t -> t.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());
        System.out.println("distinct city =" + disCity);
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        final List<Trader> cbTraders = transactions.stream()
            .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
            .map(Transaction::getTrader)
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());
        System.out.println("Cambridge traders=" + cbTraders);

        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        final String traderString = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getName)
            .distinct()
            .sorted()
            .reduce(String::join).get();
        System.out.println("traderString=" + traderString);

        //(5) 有没有交易员是在米兰工作的?
        final boolean isAnyMilanTrader = transactions.stream()
            .map(Transaction::getTrader)
            .anyMatch(t -> "Milan".equals(t.getCity()));
        System.out.println("isAnyMilanTrader=" + isAnyMilanTrader);

        //(6) 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
            .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
            .map(Transaction::getValue)
            .forEach(System.out::println);
        //(7) 所有交易中，最高的交易额是多少?
        final int maxValue = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max).get();
        System.out.println("maxValue=" + maxValue);
        //(8) 找到交易额最小的交易。
        final Transaction minTransaction = transactions.stream()
            .min(Comparator.comparing(Transaction::getValue)).get();
        System.out.println("minTransaction=" + minTransaction);
        System.out.println();

        //原始类型和包装类型
        IntStream intStream = transactions.stream().mapToInt(Transaction::getValue);
        Stream<Integer> integerStream = intStream.boxed();
        //勾股数 {3,4,5}
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100)
             .boxed()
             .flatMap(a -> IntStream.rangeClosed(a,100)
                 .filter(b -> Math.sqrt(a * a + b * b) % 1== 0)
                 .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a * a + b * b)}));
        pythagoreanTriples
            .limit(5)
            .forEach(p -> System.out.println(Arrays.toString(p)));

        System.out.println();
        IntStream.rangeClosed(1,100)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(a,100)
                .mapToObj(b -> new double[]{a,b,Math.sqrt(a * a + b * b)})
                .filter(t -> t[2] % 1 == 0)
                .map(t -> new int[]{((Double)t[0]).intValue(),((Double)t[1]).intValue(),((Double)t[2]).intValue()})
            ).forEach(p -> System.out.println(Arrays.toString(p)));
    }

}
