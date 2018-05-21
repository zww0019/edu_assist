package com.warzone.edu_assist.controller.teacher.viewhelper;

import java.util.List;

import com.warzone.edu_assist_domain_classes.NaturalClasses;

public class ViewClassesDTO {
	private int classes_id;
	private String classes_name;
	private List<ViewTask_statusDTO> task_statuses;
	private List<NaturalClasses> naturalClasses;
	private int student_sum;
	public int getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(int classes_id) {
		this.classes_id = classes_id;
	}
	public String getClasses_name() {
		return classes_name;
	}
	public void setClasses_name(String classes_name) {
		this.classes_name = classes_name;
	}
	public List<ViewTask_statusDTO> getTask_statuses() {
		return task_statuses;
	}
	public void setTask_statuses(List<ViewTask_statusDTO> task_statuses) {
		this.task_statuses = task_statuses;
	}
	public int getStudent_sum() {
		return student_sum;
	}
	public void setStudent_sum(int student_sum) {
		this.student_sum = student_sum;
	}
	public List<NaturalClasses> getNaturalClasses() {
		return naturalClasses;
	}
	public void setNaturalClasses(List<NaturalClasses> naturalClasses) {
		this.naturalClasses = naturalClasses;
	}
	
	
}
