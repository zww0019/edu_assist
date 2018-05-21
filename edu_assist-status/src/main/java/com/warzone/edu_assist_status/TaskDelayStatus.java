package com.warzone.edu_assist_status;

public enum TaskDelayStatus {
	通过("2"),
	未通过("1");
	private String status;

	public String getStatus() {
		return status;
	}

	private TaskDelayStatus(String status) {
		this.status = status;
	}
}
