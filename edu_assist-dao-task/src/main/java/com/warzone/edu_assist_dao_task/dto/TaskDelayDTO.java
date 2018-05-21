package com.warzone.edu_assist_dao_task.dto;

import java.sql.Date;

public class TaskDelayDTO {
	private int task_id;
	private String student_id;
	private String student_name;
	private Date to_date;
	private String status;
	private String verifier;
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	
	
}
