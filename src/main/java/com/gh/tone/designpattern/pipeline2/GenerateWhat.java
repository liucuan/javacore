package com.gh.tone.designpattern.pipeline2;

public class GenerateWhat extends GenerateStoryHandler  {


	@Override
	public void generateStory(Story story) {
		story.setWhat("学习设计模式");
	}

	

}
