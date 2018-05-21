package com.warzone.edu_assist_util.path;

import com.graphbuilder.struc.LinkedList.Node;

public abstract class Path {
	private static final String INITAL_PATH = "eduassist/file/upload";
	//类别
	protected abstract Catalogue catalogue();
	//目录规则
	protected abstract Director directorRule();
	
	//生成目录字符串
	private String pareRule(Director director) {
		if(director!=null) {
			return director.getName()+pareRule(director.getSub());
		}else {
			return "";
		}
	}
	public String toString() {
		return INITAL_PATH+catalogue().getCatalogue()+pareRule(directorRule());
	}
}
