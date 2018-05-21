package com.warzone.util.datamapping;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private int line;
	private List<MetaData> metaDatas = new ArrayList<MetaData>();
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public List<MetaData> getMetaDatas() {
		return metaDatas;
	}
	public void setMetaDatas(List<MetaData> metaDatas) {
		this.metaDatas = metaDatas;
	}
	
	
	
}
