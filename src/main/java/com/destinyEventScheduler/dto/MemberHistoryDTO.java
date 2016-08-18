package com.destinyEventScheduler.dto;

public class MemberHistoryDTO {

	private Long membership;
	private String name;
	private String icon;
	private int totalLikes;
	private int totalDislikes;

	public Long getMembership() {
		return membership;
	}

	public void setMembership(Long membership) {
		this.membership = membership;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public int getTotalDislikes() {
		return totalDislikes;
	}

	public void setTotalDislikes(int totalDislikes) {
		this.totalDislikes = totalDislikes;
	}
}
