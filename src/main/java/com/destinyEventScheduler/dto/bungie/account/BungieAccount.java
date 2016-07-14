package com.destinyEventScheduler.dto.bungie.account;

public class BungieAccount {

	private DestinyAccounts[] destinyAccounts;
	private BungieNetUser bungieNetUser;
	private RelatedGroups relatedGroups;

	public DestinyAccounts[] getDestinyAccounts() {
		return destinyAccounts;
	}

	public void setDestinyAccounts(DestinyAccounts[] destinyAccounts) {
		this.destinyAccounts = destinyAccounts;
	}

	public BungieNetUser getBungieNetUser() {
		return bungieNetUser;
	}

	public void setBungieNetUser(BungieNetUser bungieNetUser) {
		this.bungieNetUser = bungieNetUser;
	}

	public RelatedGroups getRelatedGroups() {
		return relatedGroups;
	}

	public void setRelatedGroups(RelatedGroups relatedGroups) {
		this.relatedGroups = relatedGroups;
	}

}
