package com.warzone.edu_assist_dao_user.dao;

import java.util.List;

import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.util.dao.BaseDAO;
@BaseDAO
public interface UserDAO {
	public User selectUserByUsernameAndAuthority(String username,String authority);
	public int updateUserByUsername(UserDTO user);
	public int deleteUserByUsername(String username);
	public int batchInsertStudentUser(List<UserDTO> userDTOs);
	public int insertUser(UserDTO userDTO);
}
