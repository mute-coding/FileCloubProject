package com.filecloud.api.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.filecloud.api.model.UserModel;


@Mapper
public interface UserRepository  {
	@Insert("INSERT INTO UserList (username,password,email,sessionID) VALUES (#{username},#{password},#{email},#{sessionID})")
	void createUser(UserModel user);
	@Select("SELECT sessionID FROM UserList WHERE username = #{username} AND password = #{password}")
	UserModel userLogin(String username,String password);
	
}
