package com.warzone.edu_assist_service_teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warzone.edu_assist_domain_teacher.Teacher;
import com.warzone.edu_assist_repository_teacher.repository.TeacherRepository;
import com.warzone.edu_assist_service_teacher.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	@Override
	public void registerTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherRepository.insertTeacher(teacher);
	}

	@Override
	public Teacher getTeacherByid(String techerid) {
		// TODO Auto-generated method stub
		return teacherRepository.getTeacher(techerid);
	}

}
