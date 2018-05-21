package com.warzone.edu_assist_role;
public enum Identity {
	学生("0"),
	教师("1");
	
	private String identity;
	
	private Identity(String identity){
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
}