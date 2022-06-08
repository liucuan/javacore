package com.gh.tone.rxjava;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * http://blog.csdn.net/qq_35064774/article/details/53057332
 */
public class Demo1 {
    public static void main(String[] args) {
        m1();
        m2();
        Flowable.just("map")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "------";
                    }
                })
                .subscribe(System.out::println);
        Flowable.just("map1")
                .map(s -> s.hashCode())
                .map(i -> i.toString())
                .subscribe(System.out::println);
    }

    private static void m1() {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                flowableEmitter.onNext("hello Rxjava");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on subscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        flowable.subscribe(subscriber);
    }

    private static void m2() {
        //just创建一个字符的发射器
        Flowable<String> flowable = Flowable.just("hello Rxjava2");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        };
        flowable.subscribe(consumer);
    }
}
