package com.warzone.edu_assist.controller.student.viewhelper;

import java.util.List;

import com.warzone.edu_assist_domain_session.Semester;

public class ViewAcademicYearDTO {
	private String start_year;
	private String end_year;
	private List<ViewSemesterDTO> semesterDTOs;
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
	public List<ViewSemesterDTO> getSemesterDTOs() {
		return semesterDTOs;
	}
	public void setSemesterDTOs(List<ViewSemesterDTO> semesterDTOs) {
		this.semesterDTOs = semesterDTOs;
	}
	
	
}
