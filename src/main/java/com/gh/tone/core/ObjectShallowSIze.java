package com.gh.tone.core;

import java.lang.instrument.Instrumentation;

public class ObjectShallowSIze {
    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP){
        inst = instP;
    }
    public static long sizeOf(Object obj) {
        return inst.getObjectSize(obj);
    }
    private static class ObjectA{
        String str;//4
        int i1;//4
        byte b1;//1
        byte b2;//1
        int i2;//4
        Object objectB;//4
        byte b3;//1
    }

    public static void main(String[] args) {
        System.out.println(ObjectShallowSIze.sizeOf(new ObjectA()));
    }
}
