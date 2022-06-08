package com.gh.tone.designpattern.pipeline;

/**
 * 基础 将aa转bb
 */
public class SecondValve implements Valve {
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
        handling = handling.replaceAll("11", "22");
        System.out.println("Second处理完：" + handling);
    }
}
