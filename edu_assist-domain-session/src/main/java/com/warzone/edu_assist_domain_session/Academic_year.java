package com.warzone.edu_assist_domain_session;

import java.util.List;

public class Academic_year {
	private String start_year;
	private String end_year;
	private List<Semester> semesters;
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
	public List<Semester> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<Semester> semesters) {
		this.semesters = semesters;
	}
	@Override
	public String toString() {
		return "academic_year [start_year=" + start_year + ", end_year=" + end_year + ", semesters=" + semesters + "]";
	}
	
}
