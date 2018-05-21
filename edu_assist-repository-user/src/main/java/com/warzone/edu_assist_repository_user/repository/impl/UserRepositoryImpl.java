package com.warzone.edu_assist_repository_user.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.warzone.edu_assist_dao_student.dao.StudentDAO;
import com.warzone.edu_assist_dao_user.dao.UserDAO;
import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_repository_user.repository.UserRepository;
@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StudentDAO studentDAO;
	@Override
	public User selectUserByUsernameAndAuthority(String username, String authority){
		User user = userDAO.selectUserByUsernameAndAuthority(username, authority);
		return user;
	}

	@Override
	@Transactional
	public boolean deleteUserByUsername(String username) {
		boolean flag = false;
		if(userDAO.deleteUserByUsername(username)==1 && studentDAO.deleteStudentByID(username)==1)
			flag=true;
		return flag;
	}

	@Override
	public boolean updateUserPasswordByUsername(UserDTO userDTO) {
		boolean flag = false;
		if(userDAO.updateUserByUsername(userDTO)==1) {
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean batchInsertUser(List<UserDTO> userDTOs) {
		boolean flag=true;
		if(userDAO.batchInsertStudentUser(userDTOs)==0)
			flag=false;
		return flag;
	}

	@Override
	public boolean insertUser(UserDTO userDTO) {
		boolean flag = false;
		if(userDAO.insertUser(userDTO)==1) {
			flag=true;
		}
		return flag;
	}

}
