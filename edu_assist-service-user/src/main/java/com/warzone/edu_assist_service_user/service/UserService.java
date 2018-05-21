package com.warzone.edu_assist_service_user.service;


import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_user.User;

public interface UserService {
	public User getUserByUsernameAndAuthority(String username,String identity);
	public boolean deleteUserByUsername(String username);
	public boolean updateUserPasswordByUsername(UserDTO userDTO);
}
