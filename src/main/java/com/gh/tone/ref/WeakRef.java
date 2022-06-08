package com.gh.tone.ref;

import java.lang.ref.WeakReference;

public class WeakRef {
    public static void main(String[] args) {
        WeakReference<String> wr = new WeakReference<String>(new String("hh"));
//        System.gc();
        System.out.println(wr.get());
    }
}
