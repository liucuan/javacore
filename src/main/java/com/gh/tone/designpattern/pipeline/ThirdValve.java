package com.gh.tone.designpattern.pipeline;

/**
 * 基础 将aa转bb
 */
public class ThirdValve implements Valve {
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
        handling = handling.replaceAll("zz", "yy");
        System.out.println("Third处理完：" + handling);
    }
}
