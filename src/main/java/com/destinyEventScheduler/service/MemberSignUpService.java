package com.destinyEventScheduler.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.adapter.BungieAccountAdapter;
import com.destinyEventScheduler.dto.bungie.account.BungieAccount;
import com.destinyEventScheduler.dto.bungie.account.BungieAccountResponse;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.bungie.BungieApiService;

@Service
public class MemberSignUpService {

	@Autowired
	private BungieApiService bungieApiService;
	
	@Autowired
	private BungieAccountAdapter bungieAccountAdapter;
	
	@Autowired
	private MemberService memberService;
	
	@Transactional
	public Member signup(Long membership, Platform platform, Long clanId) {
		BungieAccountResponse bungieAccountResponse = bungieApiService.getBungieAccount(membership, platform);
		BungieAccount bungieAccount = bungieAccountResponse.getBungieAccount();
		if(bungieAccount != null){
			Member member = bungieAccountAdapter.convertBungieAccont(bungieAccount, clanId);
			memberService.save(member);
			return member;
		}
		throw new IllegalArgumentException("Member not found");
	}
	
}
