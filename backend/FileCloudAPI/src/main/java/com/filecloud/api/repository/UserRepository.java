package com.filecloud.api.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.filecloud.api.model.UserModel;


@Mapper
public interface UserRepository  {
	@Select("SELECT * FROM user_table WHERE userName = #{username}")
	UserModel findByUserName(String username);
}
