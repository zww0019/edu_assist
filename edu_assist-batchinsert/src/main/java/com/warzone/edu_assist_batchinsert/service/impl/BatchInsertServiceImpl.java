package com.warzone.edu_assist_batchinsert.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.warzone.edu_assist_batchinsert.service.BatchInsertService;
import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_role.Identity;
import com.warzone.util.datamapping.DataMapper;
import com.warzone.util.datamapping.Model;
@Service
public class BatchInsertServiceImpl implements BatchInsertService {
	@Override
	public List<StudentDTO> getStudentList(List<Model> models) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		DataMapper<StudentDTO> dMapper = new DataMapper<>();
		List<StudentDTO> students = dMapper.DatasMapping(models, StudentDTO.class);
		return students;
	}
	@Override
	public List<UserDTO> getUserList(List<StudentDTO> students,String default_password) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		List<UserDTO> users = new ArrayList<>();
		for(StudentDTO student : students){
			String userid = student.getStudentid();
			String password = default_password;
			String identity = Identity.学生.toString();
			UserDTO user = new UserDTO();
			user.setUserid(userid);
			user.setPassword(password);
			user.setAuthority(identity);
			users.add(user);
		}
		return users;
	}


}
