package com.destinyEventScheduler.dto.bungie.clan;

import com.destinyEventScheduler.dto.bungie.account.ClanBungie;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	@JsonProperty("detail")
	private ClanBungie clanBungie;

	public ClanBungie getClanBungie() {
		return clanBungie;
	}

	public void setClanBungie(ClanBungie clanBungie) {
		this.clanBungie = clanBungie;
	}

}