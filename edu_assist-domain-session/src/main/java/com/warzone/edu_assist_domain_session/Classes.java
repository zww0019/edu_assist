package com.warzone.edu_assist_domain_session;

import java.util.List;

import com.warzone.edu_assist_domain_student.Student;

public class Classes {
	private int classes_id;
	private String classes_name;
	public int getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(int classes_id) {
		this.classes_id = classes_id;
	}
	public String getClasses_name() {
		return classes_name;
	}
	public void setClasses_name(String classes_name) {
		this.classes_name = classes_name;
	}
	@Override
	public String toString() {
		return "Classes [classes_id=" + classes_id + ", classes_name=" + classes_name + "]";
	}
	
	
	
}
