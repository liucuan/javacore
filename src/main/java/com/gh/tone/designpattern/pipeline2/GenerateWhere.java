package com.gh.tone.designpattern.pipeline2;

public class GenerateWhere extends GenerateStoryHandler {


	@Override
	public void generateStory(Story story) {
	story.setWhere("公司");
		
	}

}
