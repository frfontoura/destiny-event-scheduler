package com.destinyEventScheduler.dto.bungie.account;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RelatedGroups {

	private ClanBungie clan;
	
	@JsonAnySetter
	public void setDynamicProperty(String name, Map<String, Object> value) {
		ObjectMapper mapper = new ObjectMapper();
		clan = mapper.convertValue(value, ClanBungie.class);
	}

	public ClanBungie getClan() {
		return clan;
	}

	public void setClan(ClanBungie clan) {
		this.clan = clan;
	}

}
