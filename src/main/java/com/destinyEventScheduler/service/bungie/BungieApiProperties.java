package com.destinyEventScheduler.service.bungie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BungieApiProperties {
	
	@Value("${bungie.key}")
	private String key;

	public String getKey() {
		return key;
	}

}
