package com.destinyEventScheduler.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destinyEventScheduler.dto.bungie.account.BungieAccount;
import com.destinyEventScheduler.dto.bungie.account.ClanBungie;
import com.destinyEventScheduler.dto.bungie.account.UserInfo;
import com.destinyEventScheduler.dto.bungie.clan.BungieClan;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Member;
import com.destinyEventScheduler.service.bungie.BungieApiService;

@Service
public class BungieAccountAdapter {
	
	private static final String DEFAULT_ICON = "/img/profile/avatars/Destiny26.jpg";
	
	@Autowired
	private BungieApiService bungieApiService;
	
	public Member convertBungieAccont(BungieAccount bungieAccount, Long clanId){
		Member member = null;
		if(bungieAccount != null){
			UserInfo userInfo = bungieAccount.getDestinyAccounts()[0].getUserInfo();
			member = new Member();
			member.setName(userInfo.getDisplayName());
			member.setMembership(userInfo.getMembershipId());
			member.setPlatform(Platform.parse(userInfo.getMembershipType()));
			member.setClan(getClan(bungieAccount, clanId));
			
			if(bungieAccount.getBungieNetUser() != null){
				member.setIcon(bungieAccount.getBungieNetUser().getProfilePicturePath());
			} else {
				member.setIcon(DEFAULT_ICON);
			}
		}
		return member;
	}

	private Clan getClan(BungieAccount bungieAccount, Long clanId) {
		ClanBungie clanBungie = bungieAccount.getRelatedGroups().getClan();
		Clan clan = null;
		
		if(clanBungie == null){
			BungieClan clanById = bungieApiService.getClanById(clanId);
			clanBungie = clanById.getResponse().getClanBungie();
		}
		
		if(clanBungie != null){
			clan = new Clan();
			clan.setGroupId(clanBungie.getGroupId());
			clan.setName(clanBungie.getName());
			clan.setIcon(clanBungie.getAvatarPath());
			clan.setBackground(clanBungie.getBannerPath());
			clan.setDescription(clanBungie.getMotto());
		}
		return clan;
	}
	
}
