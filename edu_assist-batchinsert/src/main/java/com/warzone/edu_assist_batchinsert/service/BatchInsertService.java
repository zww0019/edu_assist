package com.warzone.edu_assist_batchinsert.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.util.datamapping.Model;

public interface BatchInsertService {
	public List<StudentDTO> getStudentList(List<Model> models) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException ;
	public List<UserDTO> getUserList(List<StudentDTO> students,String default_password)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException;
}
