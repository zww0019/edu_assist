package com.warzone.edu_assist_domain_user;

public class User {
	private String userid;
	private String password;
	private String authority;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password + ", authority=" + authority + "]";
	}
	
}
