package com.gh.tone.designpattern.pipeline;

public interface Pipeline {
    Valve getFirst();

    Valve getBasic();

    void setBasic(Valve valve);

    void addValve(Valve valve);
}
