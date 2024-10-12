package com.filecloud.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filecloud.api.model.UserList;
import com.filecloud.api.repository.UserListRepository;

@Service
public class UserListService {
	@Autowired
	private UserListRepository userListRepository;
	public void createUser(String username,
							String password,
							String userphone,
							String useremile) {
		UserList userList = new UserList();
		
		userList.setUser_name(username);
		userList.setPassword(password);
		userList.setUser_phone(userphone);
		userList.setUser_phone(useremile);
		userListRepository.createUser(userList);
	}
}
