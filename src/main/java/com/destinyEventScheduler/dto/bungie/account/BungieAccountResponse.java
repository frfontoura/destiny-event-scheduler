package com.destinyEventScheduler.dto.bungie.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BungieAccountResponse {

	@JsonProperty("Response")
	private BungieAccount bungieAccount;

	public BungieAccount getBungieAccount() {
		return bungieAccount;
	}

	public void setBungieAccount(BungieAccount bungieAccount) {
		this.bungieAccount = bungieAccount;
	}

}