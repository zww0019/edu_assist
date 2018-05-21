package com.warzone.edu_assist_repository_teacher.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.warzone.edu_assist_dao_teacher.dao.TeacherDAO;
import com.warzone.edu_assist_dao_teacher.dto.TeacherDTO;
import com.warzone.edu_assist_dao_user.dao.UserDAO;
import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_teacher.Teacher;
import com.warzone.edu_assist_repository_teacher.repository.TeacherRepository;
import com.warzone.edu_assist_repository_user.repository.UserRepository;
import com.warzone.edu_assist_role.Identity;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {

	@Autowired
	private TeacherDAO teacherDAO;
	@Autowired
	private UserRepository userRepository;
	@Override
	public Teacher getTeacher(String teacherid) {
		TeacherDTO teacherDTO = teacherDAO.selectTeacherByTeacherid(teacherid);
		Teacher teacher = new Teacher();
		teacher.setName(teacherDTO.getName());
		teacher.setSex(teacherDTO.getSex());
		teacher.setTeacher_id(teacherDTO.getTeacherid());
		return teacher;
	}
	@Transactional
	@Override
	public void insertTeacher(Teacher teacher) {
		String authority = Identity.教师.getIdentity();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setName(teacher.getName());
		teacherDTO.setSex(teacher.getSex());
		teacherDTO.setTeacherid(teacher.getTeacher_id());
		teacherDAO.insertTeacher(teacherDTO);
		
		
		UserDTO userdto = new UserDTO();
		userdto.setUserid(teacher.getTeacher_id());
		userdto.setPassword(teacher.getTeacher_id());
		userdto.setAuthority(authority);
		userRepository.insertUser(userdto);
	}

}
