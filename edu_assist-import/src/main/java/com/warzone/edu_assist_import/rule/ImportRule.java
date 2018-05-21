package com.warzone.edu_assist_import.rule;


public class ImportRule {
	private Column column;
	private Row row;
	
	
	
	
	public ImportRule(Column column, Row row) {
		super();
		this.column = column;
		this.row = row;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public Row getRow() {
		return row;
	}
	public void setRow(Row row) {
		this.row = row;
	}
	
	
	
	
}
