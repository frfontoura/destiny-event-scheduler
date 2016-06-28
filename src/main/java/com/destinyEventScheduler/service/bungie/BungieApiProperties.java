package com.destinyEventScheduler.service.bungie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:bungie.properties")
public class BungieApiProperties {

	@Value("${base_url}")
	private String baseUrl;
	
	@Value("${key}")
	private String key;
	
	@Value("${bungie_account}")
	private String bungieAccount;

	@Value("${bungie_memners_of_clan}")
	private String bungieMembersOfClan;
	
	public String getKey() {
		return key;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getBungieAccount(){
		return baseUrl + bungieAccount;
	}
	
	public String getBungieMembersOfClan(){
		return baseUrl + bungieMembersOfClan;
	}
}
