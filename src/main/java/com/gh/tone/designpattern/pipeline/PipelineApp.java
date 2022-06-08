package com.gh.tone.designpattern.pipeline;

public class PipelineApp {
    public static void main(String[] args) {
        String handling = "aabb1122zzyy";
        StandardPipeline pipeline = new StandardPipeline();
        BasicValve basicValve = new BasicValve();
        SecondValve secondValve = new SecondValve();
        ThirdValve thirdValve = new ThirdValve();
        pipeline.setBasic(basicValve);
        pipeline.addValve(secondValve);
        pipeline.addValve(thirdValve);
        pipeline.getFirst().invoke(handling);
    }
}
