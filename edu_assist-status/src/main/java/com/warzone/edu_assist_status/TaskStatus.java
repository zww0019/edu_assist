package com.warzone.edu_assist_status;


public enum TaskStatus {
	完成("1"),
	未完成("0");
	
	private String status;
	
	private TaskStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
