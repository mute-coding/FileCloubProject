package com.filecloud.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filecloud.api.model.UserModel;
import com.filecloud.api.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	public String createUser(String username,String password,String email) {
		String session = UUID.randomUUID().toString(); 
		UserModel user = new UserModel();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setSessionID(session);
		userRepository.createUser(user);
		System.out.println("User created with session ID: " + session);

		return session;
	}
	public UserModel userLogin(String username,String password) {
		return userRepository.userLogin(username, password);
	}
	
}
