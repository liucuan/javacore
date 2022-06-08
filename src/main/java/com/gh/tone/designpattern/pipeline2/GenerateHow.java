package com.gh.tone.designpattern.pipeline2;

public class GenerateHow extends GenerateStoryHandler {


	@Override
	public void generateStory(Story story) {
		story.setHow("需要写代码，另外看理论知识");
	
		
	}



}
