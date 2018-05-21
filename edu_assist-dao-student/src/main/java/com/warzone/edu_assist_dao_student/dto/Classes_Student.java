package com.warzone.edu_assist_dao_student.dto;

public class Classes_Student {
	private int classes_id;
	private String student_id;
	public int getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(int classes_id) {
		this.classes_id = classes_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	@Override
	public String toString() {
		return "Classes_Student [classes_id=" + classes_id + ", student_id=" + student_id + "]";
	}
	
}
