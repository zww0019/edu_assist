package com.warzone.edu_assist_service_session.vo;

public class SessionVO {
	private String start_year;
	private String end_year;
	private String semester;
	private String course;
	private String lecturer_id;
	
	public String getLecturer_id() {
		return lecturer_id;
	}
	public void setLecturer_id(String lecturer_id) {
		this.lecturer_id = lecturer_id;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
}
