package com.gh.tone.designpattern.pipeline;

public interface Valve {
    Valve getNext();
    void setNext(Valve valve);
    void invoke(String handling);
}
