package com.destinyEventScheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Member;

@Service
public class LoginService {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberSignUpService memberSignUpService;
	
	public Member login(Long membership, Platform platform) {
		Member member = memberService.getByMembership(membership);
		if(member == null){
			member = memberSignUpService.signup(membership, platform);
		}
		return member;
	}

}
