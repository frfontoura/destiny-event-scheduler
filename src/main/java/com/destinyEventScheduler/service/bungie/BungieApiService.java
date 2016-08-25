package com.destinyEventScheduler.service.bungie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.destinyEventScheduler.dto.bungie.account.BungieAccountResponse;
import com.destinyEventScheduler.dto.bungie.clan.BungieClan;
import com.destinyEventScheduler.dto.bungie.membersOfClan.MemberBungie;
import com.destinyEventScheduler.dto.bungie.membersOfClan.MembersOfClanResponse;
import com.destinyEventScheduler.enums.Platform;

@Service
public class BungieApiService {

	private static final String BASE_URL = "https://www.bungie.net/Platform/";
	private static final String BUNGIE_ACCOUNT = BASE_URL + "User/GetBungieAccount/:membership/:platform/";
	private static final String MEMBERS_OF_CLAN = BASE_URL + "Group/:groupId/ClanMembers/?memberType=-1&sort=0&platformType=:platform&currentPage=:page"; 
	private static final String GROUP_BY_ID = BASE_URL + "Group/:clanId/";
	
	@Autowired
	private BungieApiProperties bungieApiProperties;
	
	public BungieAccountResponse getBungieAccount(Long membershipId, Platform platform) {
		String url = BUNGIE_ACCOUNT.replace(":membership", membershipId.toString()).replace(":platform", platform.getValue().toString());
		return get(url, BungieAccountResponse.class);
	}

	public List<MemberBungie> getMembersOfClan(String groupId, Platform platform, Integer page){
		List<MemberBungie> members = null;
		String url = MEMBERS_OF_CLAN.replace(":groupId", groupId).replace(":platform", platform.getValue().toString()).replace(":page", page.toString());
		MembersOfClanResponse membersOfClanResponse = get(url, MembersOfClanResponse.class);
		if(membersOfClanResponse != null && membersOfClanResponse.getResponse() != null){
			members = membersOfClanResponse.getResponse().getMembers();
			if(membersOfClanResponse.getResponse().isHasMore()){
				members.addAll(getMembersOfClan(groupId, platform, page +1));
			}
		}
		return members;
	}
	
	public BungieClan getClanById(Long clanId){
		String url = GROUP_BY_ID.replace(":clanId", String.valueOf(clanId));
		return get(url, BungieClan.class);
	}
	
	private <T> T get(String url, Class<T> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		T obj = restTemplate.exchange(url, HttpMethod.GET, getHeaders(), clazz).getBody();
		return (T) obj;
	}
	
	private HttpEntity<String> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-API-KEY", bungieApiProperties.getKey());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

}
