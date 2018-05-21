package com.warzone.edu_assist.controller.student.viewhelper;

import java.util.List;

import com.warzone.edu_assist_domain_classes.Classes;

public class ViewSessionDTO {
	private String status;
	private String coursename;
	private List<ViewClassesDTO> classesDTOs;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public List<ViewClassesDTO> getClassesDTOs() {
		return classesDTOs;
	}
	public void setClassesDTOs(List<ViewClassesDTO> classesDTOs) {
		this.classesDTOs = classesDTOs;
	}
	
}
