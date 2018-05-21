package com.warzone.edu_assist_domain_session;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import com.warzone.edu_assist_domain_course.Course;
import com.warzone.edu_assist_domain_teacher.Teacher;

public class Session {
	private int session_id;
	private String session_name;
	private String completion_status;
	private Course course;
	private List<Classes> classes;
	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public String getSession_name() {
		return session_name;
	}
	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}
	public String getCompletion_status() {
		return completion_status;
	}
	public void setCompletion_status(String completion_status) {
		this.completion_status = completion_status;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Classes> getClasses() {
		return classes;
	}
	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}
	@Override
	public String toString() {
		return "Session [session_id=" + session_id + ", session_name=" + session_name + ", completion_status="
				+ completion_status + ", course=" + course + ", classes=" + classes + "]";
	}
	
	
	
	
}
