package com.warzone.edu_assist_service_student.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.util.datamapping.Model;

public interface StudentService {
	public List<Student> getStudentByClassid(int classid);
	public Student getStudentByStudentid(String studentid);
}
