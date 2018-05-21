package com.warzone.edu_assist.controller.student.viewhelper;

import java.util.List;

public class ViewClassesDTO {
	private String classesname;
	private int classesid;
	private List<ViewTaskDTO> taskDTOs;
	public String getClassesname() {
		return classesname;
	}
	public void setClassesname(String classesname) {
		this.classesname = classesname;
	}
	public int getClassesid() {
		return classesid;
	}
	public void setClassesid(int classesid) {
		this.classesid = classesid;
	}
	public List<ViewTaskDTO> getTaskDTOs() {
		return taskDTOs;
	}
	public void setTaskDTOs(List<ViewTaskDTO> taskDTOs) {
		this.taskDTOs = taskDTOs;
	}
	
}
