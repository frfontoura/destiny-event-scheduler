package com.destinyEventScheduler.adapter;

import org.springframework.stereotype.Service;

import com.destinyEventScheduler.dto.bungie.account.BungieAccount;
import com.destinyEventScheduler.dto.bungie.account.ClanBungie;
import com.destinyEventScheduler.dto.bungie.account.UserInfo;
import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.model.Clan;
import com.destinyEventScheduler.model.Member;

@Service
public class BungieAccountAdapter {
	
	public Member convertBungieAccont(BungieAccount bungieAccount){
		Member member = null;
		if(bungieAccount != null){
			UserInfo userInfo = bungieAccount.getDestinyAccounts()[0].getUserInfo();
			member = new Member();
			member.setName(userInfo.getDisplayName());
			member.setMembership(userInfo.getMembershipId());
			member.setIcon(bungieAccount.getBungieNetUser().getProfilePicturePath());
			member.setPlatform(Platform.parse(userInfo.getMembershipType()));
			member.setClan(getClan(bungieAccount));
		}
		return member;
	}

	private Clan getClan(BungieAccount bungieAccount) {
		ClanBungie clanBungie = bungieAccount.getRelatedGroups().getClan();
		Clan clan = null;
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
