package com.warzone.edu_assist_repository_teacher.repository;

import com.warzone.edu_assist_domain_teacher.Teacher;

public interface TeacherRepository {
	public Teacher getTeacher(String teacherid);
	public void insertTeacher(Teacher teacher);
}
