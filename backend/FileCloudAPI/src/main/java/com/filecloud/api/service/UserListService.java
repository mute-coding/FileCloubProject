package com.filecloud.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filecloud.api.SaltPassword.MD5util;
import com.filecloud.api.SaltPassword.UUIDutil;
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
		Date currentDate = new Date();
		
		userList.setUser_name(username);
		String salt = UUIDutil.uuid();
		userList.setSaltPassword(salt);
		String md5Password = MD5util.md5(userList.getPassword(),salt);
		userList.setPassword(md5Password);
		userList.setUser_phone(userphone);
		userList.setUser_email(useremile);
		userList.setCreated_user(username);
		userList.setCreated_time(currentDate);
		userList.setModified_user(username);
		userList.setModified_time(currentDate);
		userListRepository.createUser(userList);
	}
}
