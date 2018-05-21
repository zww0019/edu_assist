package com.warzone.edu_assist_dao_student.dto;

public class StudentDTO {
	private String studentid;
	private String name;
	private String classa;
	private String sex;
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassa() {
		return classa;
	}
	public void setClassa(String classa) {
		this.classa = classa;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "StudentDTO [studentid=" + studentid + ", name=" + name + ", classa=" + classa + ", sex=" + sex + "]";
	}
	
}
