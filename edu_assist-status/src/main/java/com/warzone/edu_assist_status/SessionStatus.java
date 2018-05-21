package com.warzone.edu_assist_status;

public enum SessionStatus {
	完结("1"),
	未完结("0");
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	private SessionStatus(String status) {
		this.status=status;
	}
}
