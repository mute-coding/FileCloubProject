package com.filecloud.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.filecloud.api.model.UserList;

@Mapper
public interface UserListRepository {
	@Insert("INSERT INTO UserList"
			+ "(user_name,password,salt,user_phone,user_email,is_delete,create_user,create_time,modified_user,modified_time)"
			+ "VALUES (#{user_name},#{password},#{salt},#{user_phone},#{user_email},#{isDelete},#{created_user},#{created_time},"
			+ "#{modified_user},#{modified_time}) ")
	void createUser(UserList userList);
	@Select("SELECT * FROM UserList WHERE user_name = #{user_name}")
	List<UserList> GetDataByUserName(String username);
	

}
