package com.warzone.edu_assist_domain_classes;

import java.util.List;


import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;

public class Classes {
	private int classes_id;
	private String classes_name;
	private List<NaturalClasses> naturalClasses;
	private int size;
	private List<Task> tasks;
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
	
	public List<NaturalClasses> getNaturalClasses() {
		return naturalClasses;
	}
	public void setNaturalClasses(List<NaturalClasses> naturalClasses) {
		this.naturalClasses = naturalClasses;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
