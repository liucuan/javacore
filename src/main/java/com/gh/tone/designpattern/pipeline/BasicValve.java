package com.gh.tone.designpattern.pipeline;

/**
 * 基础 将aa转bb
 */
public class BasicValve implements Valve {
    protected Valve next = null;

    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }

    @Override
    public void invoke(String handling) {
        handling = handling.replaceAll("aa", "bb");
        System.out.println("基础处理完：" + handling);
    }
}
