package com.gh.tone.designpattern.pipeline2;

public class GenerateWhen extends GenerateStoryHandler  {


	@Override
	public void generateStory(Story story) {
		story.setWhen("2013/9/26");
	}

	

}
