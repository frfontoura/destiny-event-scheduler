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
	public void signup(Long membership, Platform platform) {
		BungieAccountResponse bungieAccountResponse = bungieApiService.getBungieAccount(membership, platform);
		BungieAccount bungieAccount = bungieAccountResponse.getBungieAccount();
		if(bungieAccount != null){
			Member member = bungieAccountAdapter.convertBungieAccont(bungieAccount);
			memberService.save(member);
		}
	}
	
}
