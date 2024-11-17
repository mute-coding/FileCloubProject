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
		String salt = UUIDutil.uuid();  // 生成 salt
		userList.setSaltPassword(salt);
		// 使用傳入的 password 加密
		String md5Password = MD5util.md5(password, salt);
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
	    List<UserList> userList = userListRepository.GetDataByUserName(username);

	    if (userList == null || userList.isEmpty()) {
	        throw new IllegalArgumentException("使用者帳戶不存在");
	    }

	    UserList userData = userList.get(0);

	    if (userData.getIsDelete() != null && userData.getIsDelete() == 1) {
	        throw new IllegalArgumentException("使用者帳戶已被刪除");
	    }

	    String salt = userData.getSaltPassword();
	    String md5Password = MD5util.md5(password, salt);

	    System.out.println("原始密碼: " + password);
	    System.out.println("加密用 salt: " + salt);
	    System.out.println("加密後的密碼: " + md5Password);
	    System.out.println("資料庫密碼: " + userData.getPassword());

	    if (!userData.getPassword().equals(md5Password)) {
	        System.out.println("存儲密碼與計算密碼不一致");
	        throw new IllegalArgumentException("密碼不匹配，請檢查輸入是否正確");
	    }

	    return userList;
    }
	
	public List<UserList> GetDataByUserName(String username){
		return userListRepository.GetDataByUserName(username);
	}
}
