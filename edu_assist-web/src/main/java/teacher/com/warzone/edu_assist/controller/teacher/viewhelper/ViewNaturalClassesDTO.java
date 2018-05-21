package com.warzone.edu_assist.controller.teacher.viewhelper;

import java.util.List;

public class ViewNaturalClassesDTO {
	private String name;
	private List<ViewTask_statusDTO> task_statusDTOs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ViewTask_statusDTO> getTask_statusDTOs() {
		return task_statusDTOs;
	}
	public void setTask_statusDTOs(List<ViewTask_statusDTO> task_statusDTOs) {
		this.task_statusDTOs = task_statusDTOs;
	}
	
}
