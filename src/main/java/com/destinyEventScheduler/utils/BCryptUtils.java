package com.destinyEventScheduler.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.base.Strings;

public abstract class BCryptUtils {

	public static String getHashedPassword(String password) {
		if (!Strings.isNullOrEmpty(password)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			password = passwordEncoder.encode(password);
		}
		return password;
	}

}