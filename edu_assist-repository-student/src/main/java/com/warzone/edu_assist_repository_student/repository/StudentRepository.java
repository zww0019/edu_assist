package com.warzone.edu_assist_repository_student.repository;

import java.util.List;

import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_domain_student.Student;
public interface StudentRepository{
	public boolean addStudent(StudentDTO studentDTO,String defaultPassword);
	public boolean deleteStudentbyStudentid(String studentid);
	public boolean batchInsertStudentInfo(List<StudentDTO> studentDTOs,int classesid);
	public List<Student> getStudentByClassid(int classesid);
	public Student getStudent(String studentid);
}
