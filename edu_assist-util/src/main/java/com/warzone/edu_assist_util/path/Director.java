package com.warzone.edu_assist_util.path;

public class Director {
	private String name;
	private Director sub;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Director getSub() {
		return sub;
	}
	public void setSub(Director sub) {
		this.sub = sub;
	}
	
	
}
