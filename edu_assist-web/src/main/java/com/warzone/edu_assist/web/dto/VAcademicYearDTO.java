package com.warzone.edu_assist.web.dto;

import java.util.List;

public class VAcademicYearDTO {
	private String start_year;
	private String end_year;
	private List<VSemesterDTO> vSemesterDTOs;
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
	public List<VSemesterDTO> getvSemesterDTOs() {
		return vSemesterDTOs;
	}
	public void setvSemesterDTOs(List<VSemesterDTO> vSemesterDTOs) {
		this.vSemesterDTOs = vSemesterDTOs;
	}
	
}
