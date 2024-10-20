package com.filecloud.api.model;

import java.util.Date;

public class UserList {
	private int user_id;
	private String user_name;
	private String password ;
	private String salt;
	private String user_phone;
	private String user_email ;
	private Integer isDelete;
    private String created_user ;
    private Date created_time;
    private String modified_user;
    private Date modified_time;
	public UserList() {}
	public UserList(int user_id, String user_name, String password, String salt, String user_phone,
			String user_email, Integer isDelete, String created_user, Date created_time, String modified_user,
			Date modified_time) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.salt = salt;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.isDelete = isDelete;
		this.created_user = created_user;
		this.created_time = created_time;
		this.modified_user = modified_user;
		this.modified_time = modified_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSaltPassword() {
		return salt;
	}
	public void setSaltPassword(String salt) {
		this.salt = salt;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public Date getModified_time() {
		return modified_time;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
	
}
