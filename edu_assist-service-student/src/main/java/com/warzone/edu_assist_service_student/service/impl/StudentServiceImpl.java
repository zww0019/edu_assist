package com.warzone.edu_assist_service_student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_repository_student.repository.StudentRepository;
import com.warzone.edu_assist_service_student.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepositoy;
	
	@Override
	public List<Student> getStudentByClassid(int classid) {
		return studentRepositoy.getStudentByClassid(classid);
	}

	@Override
	public Student getStudentByStudentid(String studentid) {
		// TODO Auto-generated method stub
		return studentRepositoy.getStudent(studentid);
	}



}
