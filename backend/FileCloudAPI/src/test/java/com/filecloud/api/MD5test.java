package com.filecloud.api;

import com.filecloud.api.SaltPassword.MD5util;

public class MD5test {
	  public static void main(String[] args) {
		  String password = "rainey1085123";
		    String salt = "e96f81d53af44f86b963d2eb07f83a90";

		    String encryptedPassword = MD5util.md5(password, salt);
		    System.out.println("測試加密結果: " + encryptedPassword);
	    }
}
