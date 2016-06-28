package com.destinyEventScheduler.dto.bungie.account;

public class BungieAccount {

	private DestinyAccounts[] destinyAccounts;
	private String destinyAccountResult;
	private Clans[] clans;
	private BungieNetUser bungieNetUser;
	private RelatedGroups relatedGroups;

	public DestinyAccounts[] getDestinyAccounts() {
		return destinyAccounts;
	}

	public void setDestinyAccounts(DestinyAccounts[] destinyAccounts) {
		this.destinyAccounts = destinyAccounts;
	}

	public String getDestinyAccountResult() {
		return destinyAccountResult;
	}

	public void setDestinyAccountResult(String destinyAccountResult) {
		this.destinyAccountResult = destinyAccountResult;
	}

	public Clans[] getClans() {
		return clans;
	}

	public void setClans(Clans[] clans) {
		this.clans = clans;
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
