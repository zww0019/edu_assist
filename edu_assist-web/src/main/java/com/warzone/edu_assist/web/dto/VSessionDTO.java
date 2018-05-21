package com.warzone.edu_assist.web.dto;

import java.util.List;

public class VSessionDTO {
	private String course;
	private String teacher_id;
	private String status;
	private List<VClassesDTO> classesDTOs;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<VClassesDTO> getClassesDTOs() {
		return classesDTOs;
	}
	public void setClassesDTOs(List<VClassesDTO> classesDTOs) {
		this.classesDTOs = classesDTOs;
	}
	
}
