package com.warzone.edu_assist.web.dto;

import java.util.List;

public class Node {
	private String text;
	private List<Node> nodes;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
}
