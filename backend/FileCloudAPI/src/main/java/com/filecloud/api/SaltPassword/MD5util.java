package com.filecloud.api.SaltPassword;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class MD5util {
	public static String md5(String src,String salt) {
		String result=src+ salt;
        return DigestUtils.md5DigestAsHex(result.getBytes());
	}
}
