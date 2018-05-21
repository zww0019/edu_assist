package com.warzone.edu_assist_dao_task.dto;

import java.sql.Date;
import java.util.List;

public class TaskDTO {
	private String task_name;
	private String task_directory;
	private int task_id;
	private int classes_id;
	private Date end_date;
	private Date pub_date;
	private String task_content;
	private String affix_position;
	private List<TaskComplectionDTO> taskComplectionDTOs;
	private List<TaskDelayDTO> taskDelayDTOs;
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_directory() {
		return task_directory;
	}
	public void setTask_directory(String task_directory) {
		this.task_directory = task_directory;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public int getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(int classes_id) {
		this.classes_id = classes_id;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getPub_date() {
		return pub_date;
	}
	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public List<TaskComplectionDTO> getTaskComplectionDTOs() {
		return taskComplectionDTOs;
	}
	public void setTaskComplectionDTOs(List<TaskComplectionDTO> taskComplectionDTOs) {
		this.taskComplectionDTOs = taskComplectionDTOs;
	}
	public List<TaskDelayDTO> getTaskDelayDTOs() {
		return taskDelayDTOs;
	}
	public void setTaskDelayDTOs(List<TaskDelayDTO> taskDelayDTOs) {
		this.taskDelayDTOs = taskDelayDTOs;
	}
	public String getAffix_position() {
		return affix_position;
	}
	public void setAffix_position(String affix_position) {
		this.affix_position = affix_position;
	}
	
}
