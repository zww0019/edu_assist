package com.warzone.edu_assist_dao_session.dto;

public class SessionDTO {
	private int session_id;
	private String lecturer_id;
	private String session_name;
	private int course_id;
	private String completion_status;
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public String getLecturer_id() {
		return lecturer_id;
	}
	public void setLecturer_id(String lecturer_id) {
		this.lecturer_id = lecturer_id;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCompletion_status() {
		return completion_status;
	}
	public void setCompletion_status(String completion_status) {
		this.completion_status = completion_status;
	}
	
}
