package com.filecloud.api.model;

public class UserModel {
	private int id;
	private String userName;
	private String userPassword;
	private String role;
	public UserModel() {}
	public UserModel(int id, String userName, String userPassword, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", role=" + role
				+ "]";
	}
	
}
