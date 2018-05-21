package com.warzone.edu_assist_service_teacher.service;

import com.warzone.edu_assist_domain_teacher.Teacher;

public interface TeacherService {
	public void registerTeacher(Teacher teacher);
	public Teacher getTeacherByid(String techerid);
}
