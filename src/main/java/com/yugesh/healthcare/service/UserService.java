package com.yugesh.healthcare.service;

import java.util.Optional;

import com.yugesh.healthcare.entity.User;


public interface UserService {

	Long saveUser(User user);
	Optional<User> findByUsername(String username);
	void updateUserPwd(String pwd,Long userId);
}
