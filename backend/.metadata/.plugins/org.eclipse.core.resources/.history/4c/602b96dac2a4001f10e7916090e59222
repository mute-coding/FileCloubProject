package com.filecloud.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		userList.setIsDelete(0);
		userList.setCreated_user(username);
		userList.setCreated_time(currentDate);
		userList.setModified_user(username);
		userList.setModified_time(currentDate);
		userListRepository.createUser(userList);
	}
	public List<UserList> login(String username,String password){
		List<UserList> userList = new ArrayList<>();
		userList = userListRepository.GetDataByUserName(username);
		UserList userData = userList.get(0);
		if(userListRepository.GetDataByUserName(username) == null) {
			throw new IllegalArgumentException("使用者帳戶不存在");
		}
		if(userData.getIsDelete() == 1) {
			throw new IllegalArgumentException("使用者帳戶已被刪除");
		}
		String salt = userData.getSaltPassword();
		String md5Psaaword = MD5util.md5(password, salt);
		if(!userData.getPassword().equals(md5Psaaword)) {
			throw new IllegalArgumentException("密碼輸入錯誤，請重新輸入");
		}
		return userList;
	}
	public List<UserList> GetDataByUserName(String username){
		return userListRepository.GetDataByUserName(username);
	}
}
