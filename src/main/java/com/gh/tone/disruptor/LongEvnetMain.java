package com.gh.tone.disruptor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

public class LongEvnetMain {
    public static void main(String[] args) throws InterruptedException {
//        Executor executor = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("disruptor-pool-%d").build();
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, threadFactory, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> System.out.println("Event: " + event)));
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; true; i++) {
            bb.putLong(0, i);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
