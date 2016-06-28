package com.destinyEventScheduler.dto.bungie.account;

public class DestinyAccounts {
	
	private String versions;
	private String clanTag;
	private String lastPlayed;
	private String clanName;
	private UserInfo userInfo;
	private Characters[] characters;
	private String grimoireScore;

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getClanTag() {
		return clanTag;
	}

	public void setClanTag(String clanTag) {
		this.clanTag = clanTag;
	}

	public String getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(String lastPlayed) {
		this.lastPlayed = lastPlayed;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Characters[] getCharacters() {
		return characters;
	}

	public void setCharacters(Characters[] characters) {
		this.characters = characters;
	}

	public String getGrimoireScore() {
		return grimoireScore;
	}

	public void setGrimoireScore(String grimoireScore) {
		this.grimoireScore = grimoireScore;
	}

}