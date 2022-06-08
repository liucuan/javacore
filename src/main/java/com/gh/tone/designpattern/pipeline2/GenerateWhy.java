package com.gh.tone.designpattern.pipeline2;

public class GenerateWhy extends GenerateStoryHandler {

	@Override
	public void generateStory(Story story) {

		story.setWhy("因为工作对设计模式的需求");

	}

}
