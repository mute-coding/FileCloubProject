package com.filecloud.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filecloud.api.model.UserModel;
import com.filecloud.api.service.LoginService;



@RestController
@RequestMapping("/filecloud")
public class LoginController {
	@Autowired
	private LoginService loginService;
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserModel user) {
		loginService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body("註冊成功");
	}
	@GetMapping("/login")
	public ResponseEntity<String> userLogin(@RequestParam String username, @RequestParam String password){
		UserModel user = loginService.userLogin(username, password);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body("登入成功");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入失敗");
		}
	}
	
}
