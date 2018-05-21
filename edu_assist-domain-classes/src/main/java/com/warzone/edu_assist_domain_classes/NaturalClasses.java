package com.warzone.edu_assist_domain_classes;

import java.util.List;

import com.warzone.edu_assist_domain_student.Student;

public class NaturalClasses {
	private String name;
	private List<Student> students;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
