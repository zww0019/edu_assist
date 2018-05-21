package com.warzone.edu_assist.controller.student.viewhelper;

import java.sql.Date;

public class ViewTaskDTO {
	private String task_name;
	private int task_id;
	private String task_content;
	private String status;
	private Date end_date;
	private String task_directory;
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getTask_directory() {
		return task_directory;
	}
	public void setTask_directory(String task_directory) {
		this.task_directory = task_directory;
	}
	
}
