package com.warzone.edu_assist_service_user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warzone.edu_assist_dao_user.dto.UserDTO;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_repository_user.repository.UserRepository;
import com.warzone.edu_assist_role.Identity;
import com.warzone.edu_assist_service_user.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public User getUserByUsernameAndAuthority(String username, String identity) {
		return userRepository.selectUserByUsernameAndAuthority(username, identity);
	}

	@Override
	public boolean deleteUserByUsername(String username) {
		return userRepository.deleteUserByUsername(username);
	}

	@Override
	public boolean updateUserPasswordByUsername(UserDTO userDTO) {
		return userRepository.updateUserPasswordByUsername(userDTO);
	}


}
