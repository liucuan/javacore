package com.gh.tone.hcpd.c4;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 模拟小于20充值，只充值一次
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference(19,0);
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int stamp = money.getStamp();
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Integer m = money.getReference();
                        if (m<20) {
                            if(money.compareAndSet(m,m+20,stamp,stamp+1)){
                                System.out.println("余额小于20，充值成功，余额:"+money.getReference()+"元");
                                break;
                            }else {
                                // 余额大于20无需充值
                                break;
                            }
                        }
                    }
                }
            }.start();
        }
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    while (true){
                        int stamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m>10) {
                            System.out.println("余额大于10");
                            if(money.compareAndSet(m,m-10,stamp,stamp+1)){
                                System.out.println("成功消费10元，余额:"+money.getReference()+"元");
                                break;
                            }else {
                                // 余额大于20无需充值
                                System.out.println("金额不足");
                                break;
                            }
                        }
                        try{
                            Thread.sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }.start();
    }
}
