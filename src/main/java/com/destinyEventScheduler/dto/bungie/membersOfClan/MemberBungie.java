package com.destinyEventScheduler.dto.bungie.membersOfClan;

public class MemberBungie {
	
	private DestinyUserInfo destinyUserInfo;
	private String membershipId;
	private String membershipType;
	private String groupId;
	private String hasPendingApplication;
	private String memberType;
	private String hasRated;
	private String isOriginalFounder;
	private String isMember;
	private String rating;
	private BungieNetUserInfo bungieNetUserInfo;

	public DestinyUserInfo getDestinyUserInfo() {
		return destinyUserInfo;
	}

	public void setDestinyUserInfo(DestinyUserInfo destinyUserInfo) {
		this.destinyUserInfo = destinyUserInfo;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getHasPendingApplication() {
		return hasPendingApplication;
	}

	public void setHasPendingApplication(String hasPendingApplication) {
		this.hasPendingApplication = hasPendingApplication;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getHasRated() {
		return hasRated;
	}

	public void setHasRated(String hasRated) {
		this.hasRated = hasRated;
	}

	public String getIsOriginalFounder() {
		return isOriginalFounder;
	}

	public void setIsOriginalFounder(String isOriginalFounder) {
		this.isOriginalFounder = isOriginalFounder;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public BungieNetUserInfo getBungieNetUserInfo() {
		return bungieNetUserInfo;
	}

	public void setBungieNetUserInfo(BungieNetUserInfo bungieNetUserInfo) {
		this.bungieNetUserInfo = bungieNetUserInfo;
	}

}