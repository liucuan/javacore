package com.gh.tone.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    public void onData(ByteBuffer bb) {
        //Grab the next sequence
        long sequence = ringBuffer.next();
        try{
            //Get the entry in the Disruptor
            LongEvent event = ringBuffer.get(sequence);
            //Fill with Data
            event.set(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
