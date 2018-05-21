package com.warzone.edu_assist_repository_student.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.warzone.edu_assist_batchinsert.service.BatchInsertService;
import com.warzone.edu_assist_dao_student.dao.StudentDAO;
import com.warzone.edu_assist_dao_student.dto.Classes_Student;
import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_user.dao.UserDAO;
import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_repository_student.repository.StudentRepository;
import com.warzone.edu_assist_repository_user.repository.UserRepository;
import com.warzone.edu_assist_role.Identity;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BatchInsertService batchInsertService;

	@Override
	@Transactional
	public boolean addStudent(StudentDTO studentDTO, String defaultPassword) {
		boolean flag = false;
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(studentDTO.getStudentid());
		userDTO.setPassword(defaultPassword);
		userDTO.setAuthority(Identity.学生.toString());
		if (studentDAO.insertStudent(studentDTO) != 0 && userRepository.insertUser(userDTO))
			flag = true;
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteStudentbyStudentid(String studentid) {
		studentDAO.deleteStudentByID(studentid);
		userRepository.deleteUserByUsername(studentid);
		return true;
	}

	@Override
	@Transactional
	public boolean batchInsertStudentInfo(List<StudentDTO> studentDTOs, int classesid) {
		List<StudentDTO> temp = new ArrayList<>();
		temp.addAll(studentDTOs);
		List<StudentDTO> will_add_list = filteExistStudent(temp);
		List<UserDTO> userDTOs = new ArrayList<>();
		// classes和student的关联列表
		List<Classes_Student> ascSCs = new ArrayList<>();
		if (!will_add_list.isEmpty()) {
			studentDAO.batchInsertStudentInfo(will_add_list);
			// 此时的遍历使用经过过滤的will_add_list，因此德大的User列表也已经过滤
			// 因此，这里的事物的作用就是保证studnet表，user表，student_classes表，三表绝对一致。
			for (StudentDTO studentDTO : will_add_list) {
				UserDTO userDto = new UserDTO();
				userDto.setUserid(studentDTO.getStudentid());
				userDto.setPassword(studentDTO.getStudentid());
				userDto.setAuthority(Identity.学生.getIdentity());
				userDTOs.add(userDto);
			}
			userRepository.batchInsertUser(userDTOs);
		}
		// 显然，插入学生与班级关联表时不能使用过滤后的studnets列表了。一个学生可以对应多个教学班
		List<StudentDTO> assocation_list = filteExistAssocationStudent_classes(classesid, studentDTOs);
		if (!assocation_list.isEmpty()) {
			for (StudentDTO studentDTO : studentDTOs) {
				Classes_Student ascsc = new Classes_Student();
				ascsc.setClasses_id(classesid);
				ascsc.setStudent_id(studentDTO.getStudentid());
				ascSCs.add(ascsc);
			}
			studentDAO.batchInsertAssocationClassesAndStudent(ascSCs);
		}
		return true;
	}

	@Override
	public List<Student> getStudentByClassid(int classesid) {
		List<StudentDTO> studentDTOs=studentDAO.selectStudentByClassid(classesid);
		List<Student> students = new ArrayList<>();
		for(StudentDTO studentDTO : studentDTOs) {
			Student student = new Student();
			student.setClassa(studentDTO.getClassa());
			student.setName(studentDTO.getName());
			student.setSex(studentDTO.getSex());
			student.setStudentid(studentDTO.getStudentid());
			students.add(student);
		}
		return students;
	}
	//这个方法需要改进
	private List<StudentDTO> filteExistStudent(List<StudentDTO> original) {
		List<StudentDTO> remove_list = new ArrayList<>();
		for (StudentDTO studentDTO : original) {
			StudentDTO student = studentDAO.selectStudentByStudentid(studentDTO.getStudentid());
			if (student != null) {
				remove_list.add(studentDTO);
			}
		}
		original.removeAll(remove_list);
		return original;
	}

	private List<StudentDTO> filteExistAssocationStudent_classes(int classesid, List<StudentDTO> studentDTOs) {
		List<Student> existAssocation = getStudentByClassid(classesid);
		List<StudentDTO> remove_list = new ArrayList<>();
		for (StudentDTO orin : studentDTOs) {
			for (Student student : existAssocation) {
				if (student.getStudentid().equals(orin.getStudentid())) {
					remove_list.add(orin);
				}
			}
		}
		studentDTOs.removeAll(remove_list);
		return studentDTOs;
	}

	@Override
	public Student getStudent(String studentid) {
		StudentDTO studentDTO = studentDAO.selectStudentByStudentid(studentid);
		Student student = new Student();
		student.setClassa(studentDTO.getClassa());
		student.setName(studentDTO.getName());
		student.setSex(studentDTO.getSex());
		student.setStudentid(studentDTO.getStudentid());
		
		return student;
	}

}
