package com.gh.tone.designpattern.pipeline2;

public class Test {
	/**in my pipeline pattern,when remove something is wrong
	 */
	public static void main(String[] args) {
		Story story = new Story();
		GenerateStoryHandler when = new GenerateWhen();
		GenerateStoryHandler where = new GenerateWhere();
		GenerateStoryHandler who = new GenerateWho();
		GenerateStoryHandler what = new GenerateWhat();
		GenerateStoryHandler why = new GenerateWhy();
		GenerateStoryHandler how = new GenerateHow();
	
		PipeLine.getPipeLine().addLast(when);
		PipeLine.getPipeLine().addLast(where);
		PipeLine.getPipeLine().addLast(who);
		PipeLine.getPipeLine().addLast(what);
		PipeLine.getPipeLine().addLast(why);
		PipeLine.getPipeLine().addLast(how);
		PipeLine.getPipeLine().genOrder();
		when.handlerMsg(story);
		System.out.println(story.getWhen() + " " + story.getWhere()+ " " + story.getWho()
				+ " " + story.getWhat()+ " " + story.getWhy() + " " + story.getHow());
		story.clear();
		PipeLine.getPipeLine().remove(how);
		PipeLine.getPipeLine().remove(what);
		PipeLine.getPipeLine().genOrder();
		when.handlerMsg(story);
	
		System.out.println(story.getWhen() + " " + story.getWhere()+ " " + story.getWho()
				+ " " + story.getWhat()+ " " + story.getWhy() + " " + story.getHow());
	}

}
