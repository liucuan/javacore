package com.gh.tone.rxjava;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);
        Flowable.fromIterable(list)
                .subscribe(System.out::println);

        Flowable.just(list)
                .subscribe(nums -> {
                    Observable.fromIterable(nums)
                            .subscribe(num -> System.out.println(num));
                });

        Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
        System.out.println("--------只要大于5的数据------------");
        Flowable.fromArray(1, 20, -1, -4, 0, 8)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer.intValue() > 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
        //只要2个数据
        System.out.println("--------只要2个数据------------");
        Flowable.fromArray(1, 2, 3, 4)
                .take(2)
                .subscribe(System.out::println);
        //在订阅者接收到数据前干点事情，
        System.out.println("--------在订阅者接收到数据前干点事情，------------");
        Flowable.just(1, 2, 3)
                .doOnNext(i -> System.out.println("save:" + i))
                .subscribe(System.out::println);
    }
}
