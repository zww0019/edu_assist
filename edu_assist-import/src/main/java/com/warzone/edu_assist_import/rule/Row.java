package com.warzone.edu_assist_import.rule;

public class Row {
	private int start;
	private int end;
	
	
	
	
	public Row(int start, int end) {
		super();
		this.start = start-1;
		this.end = end-1;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
