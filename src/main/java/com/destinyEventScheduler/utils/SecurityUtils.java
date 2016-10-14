package com.destinyEventScheduler.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.destinyEventScheduler.model.Member;

public class SecurityUtils {
	
	public static Member getCurrentMember(){
		return (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static Long getCurrentMembership(){
		return getCurrentMember().getMembership();
	}

}
