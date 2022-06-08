package com.gh.tone.designpattern.pipeline2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenqidou
 * @date 2013-9-29
 * 单例模式与管道模式结合*/
public class PipeLine {
	private List<GenerateStoryHandler> pipeList = new ArrayList<GenerateStoryHandler>();
	private static PipeLine pipeLine=null;//单例模式第一步
	private PipeLine(){}//单例模式第二部
	public static synchronized PipeLine getPipeLine(){   //单利模式第三步
		if(pipeLine==null){
			pipeLine=new PipeLine();
		}
		return pipeLine;
	}

	public  void addLast(GenerateStoryHandler handler) {
		pipeList.add(handler);
		
	}
	
	public  void genOrder() {
		if (pipeList.size() > 0) {
			for (int i = 0; i < pipeList.size(); i++) {
	//			pipeList.get(i).setNextHandler(null);   //这一步不设置为空，会出问题???需要解决
				if (i == (pipeList.size() - 1)){
					pipeList.get(i).setNextHandler(null);//最后一个需要移出原来的索引
					return;
				}
				int j=i+1;
				pipeList.get(i).setNextHandler(pipeList.get(j));
			}
		}
		
	}

	public  void remove(GenerateStoryHandler handler) {
		if (pipeList.contains(handler)) {
			pipeList.remove(handler);
		}
	}
}
