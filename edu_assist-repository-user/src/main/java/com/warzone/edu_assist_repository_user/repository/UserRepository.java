package com.warzone.edu_assist_repository_user.repository;

import java.util.List;

import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_user.User;

public interface UserRepository {
	public User selectUserByUsernameAndAuthority(String username,String authority);
	public boolean deleteUserByUsername(String username);
	public boolean updateUserPasswordByUsername(UserDTO userDTO);
	public boolean batchInsertUser(List<UserDTO> userDTOs);
	public boolean insertUser(UserDTO userDTO);
}
