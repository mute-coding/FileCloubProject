package com.filecloud.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filecloud.api.service.UserListService;

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
}
