package com.warzone.edu_assist.controller.teacher.viewhelper;

import java.util.List;

import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_domain_student.Student;

public class ViewTask_statusDTO {
	private int task_id;
	private String task_name;
	private String task_content;
	private int complete_count;
	private int uncomplete_count;
	private List<ViewStudentDTO> complete_namelist;
	private List<ViewStudentDTO> uncomplete_namelist;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public int getComplete_count() {
		return complete_count;
	}
	public void setComplete_count(int complete_count) {
		this.complete_count = complete_count;
	}
	public int getUncomplete_count() {
		return uncomplete_count;
	}
	public void setUncomplete_count(int uncomplete_count) {
		this.uncomplete_count = uncomplete_count;
	}
	public List<ViewStudentDTO> getComplete_namelist() {
		return complete_namelist;
	}
	public void setComplete_namelist(List<ViewStudentDTO> complete_namelist) {
		this.complete_namelist = complete_namelist;
	}
	public List<ViewStudentDTO> getUncomplete_namelist() {
		return uncomplete_namelist;
	}
	public void setUncomplete_namelist(List<ViewStudentDTO> uncomplete_namelist) {
		this.uncomplete_namelist = uncomplete_namelist;
	}
	
}
