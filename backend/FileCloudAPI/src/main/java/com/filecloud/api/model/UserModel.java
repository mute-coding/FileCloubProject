package com.filecloud.api.model;

public class UserModel {
	private int id;
	private String username;
	private String password;
	private String email;
	private String sessionID;
	public UserModel() {}
	public UserModel(int id,String username,String password,String email,String sessionID) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.sessionID = sessionID;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
}
