package com.warzone.edu_assist_import.rule;

public class Column {
	private int start;
	private int end;
	private String[] fields;
	
	
	
	
	public Column(int start, int end, String[] fields) {
		super();
		this.start = start-1;
		this.end = end;
		this.fields = fields;
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
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	
	
	
}
