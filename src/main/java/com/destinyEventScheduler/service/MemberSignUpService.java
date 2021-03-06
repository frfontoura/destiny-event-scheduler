package com.destinyEventScheduler.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.adapter.BungieAccountAdapter;
import com.destinyEventScheduler.dto.bungie.account.BungieAccount;
import com.destinyEventScheduler.dto.bungie.account.BungieAccountResponse;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.model.Role;
import com.destinyEventScheduler.service.bungie.BungieApiService;
import com.destinyEventScheduler.utils.BCryptUtils;
import com.destinyEventScheduler.utils.CipherUtils;

@Service
public class MemberSignUpService {

	private static final Long ROLE_USER_ID = 2L;
	
	@Autowired
	private BungieApiService bungieApiService;
	
	@Autowired
	private BungieAccountAdapter bungieAccountAdapter;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CipherUtils encryptUtils;
	
	@Transactional
	public Member signup(Long membership, Platform platform, Long clanId) throws Exception {
		BungieAccountResponse bungieAccountResponse = bungieApiService.getBungieAccount(membership, platform);
		BungieAccount bungieAccount = bungieAccountResponse.getBungieAccount();
		if(bungieAccount != null){
			Member member = bungieAccountAdapter.convertBungieAccont(bungieAccount, clanId);
			setDefaultRoles(member);
			String password = encryptUtils.encrypt(membership.toString());
			member.setPassword(BCryptUtils.getHashedPassword(password));
			memberService.save(member);
			return member;
		}
		throw new IllegalArgumentException("Member not found");
	}
	
	private void setDefaultRoles(Member member){
		member.setRoles(new ArrayList<>());
		member.getRoles().add(new Role(ROLE_USER_ID));
	}
	
}
