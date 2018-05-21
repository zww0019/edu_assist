package com.warzone.edu_assist_dao_academicyear.dto;

public class AcademicYearDTO {
	private String start_year;
	private String end_year;
	private String semester;
	private int session_id;
	
	public AcademicYearDTO() {}
	public AcademicYearDTO(String start_year, String end_year, String semester) {
		super();
		this.start_year = start_year;
		this.end_year = end_year;
		this.semester = semester;
	}
	public String getStart_year() {
		return start_year;
	}
	public void setStart_year(String start_year) {
		this.start_year = start_year;
	}
	public String getEnd_year() {
		return end_year;
	}
	public void setEnd_year(String end_year) {
		this.end_year = end_year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	
}
