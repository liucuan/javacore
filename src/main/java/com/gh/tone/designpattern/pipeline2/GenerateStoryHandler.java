package com.gh.tone.designpattern.pipeline2;
/**
 * @author chenqidou
 * @date 2013/9/2
 * 管道模式核心处理类*/
public abstract class GenerateStoryHandler {
	GenerateStoryHandler handler=null;
	//这个方法重复调用，于是乎形成管道的模型
	public void handlerMsg(Story story){
		this.generateStory(story);

		if(handler!=null){
			this.handler.handlerMsg(story);
		}
	}
	public void setNextHandler(GenerateStoryHandler handler){
		this.handler=handler;
	}

	public abstract void generateStory(Story story);

}
