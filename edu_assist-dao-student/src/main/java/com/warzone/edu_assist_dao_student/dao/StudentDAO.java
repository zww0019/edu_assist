package com.warzone.edu_assist_dao_student.dao;

import java.util.List;

import com.warzone.edu_assist_dao_student.dto.Classes_Student;
import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.util.dao.BaseDAO;
@BaseDAO
public interface StudentDAO {
	public int insertStudent(StudentDTO studentdto);
	public int deleteStudentByID(String student_id);
	public int batchInsertStudentInfo(List<StudentDTO> studentDTOs);
	public List<StudentDTO> selectStudentByClassid(int classid);
	public int batchInsertAssocationClassesAndStudent(List<Classes_Student> classes_Students);
	public StudentDTO selectStudentByStudentid(String studentid);
}
