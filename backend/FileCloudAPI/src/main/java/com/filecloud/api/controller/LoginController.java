package com.filecloud.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filecloud.api.model.UserList;
import com.filecloud.api.service.UserListService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserListService userListService;
	@PostMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestParam("username") String username,
											 @RequestParam("password") String password,
											 @RequestParam("userphone")String userphone,
											 @RequestParam("useremile")String useremile){
		userListService.createUser(username, password, userphone, useremile);
		return ResponseEntity.status(HttpStatus.CREATED).body("帳戶創建成功");
		
	}
	@PostMapping("/userlogin")	
	public List<UserList> userlogin(HttpSession session,@RequestBody UserList user){
		List<UserList> userList = new ArrayList<>();
		userList = userListService.login(user.getUser_name(),user.getPassword());
		session.setAttribute("user_id",userList.get(0).getUser_id());
		session.setAttribute("username",userList.get(0).getUser_name());
		return (userList);
		
	}
}
