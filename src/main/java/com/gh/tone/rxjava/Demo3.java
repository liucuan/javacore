package com.gh.tone.rxjava;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class Demo3 {
    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                flowableEmitter.onNext("exception:"+(1/0));
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
        .subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                flowableEmitter.onNext("将会在3秒后显示");
                TimeUnit.SECONDS.sleep(3);
                flowableEmitter.onNext("---haha---");
                flowableEmitter.onComplete();
            }
        },BackpressureStrategy.BUFFER)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation());
    }
}
