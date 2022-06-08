package com.gh.tone.designpattern.pipeline2;

public class GenerateWho extends GenerateStoryHandler {


	@Override
	public void generateStory(Story story) {
		story.setWho("CQD");
	}

}
