package com.destinyEventScheduler.dto;

import java.util.Map;

public class MemberHistoryDTO {

	private Long membership;
	private String name;
	private String icon;
	private int totalLikes;
	private int totalDislikes;
	private Map<String, String> memberTitle;

	public MemberHistoryDTO() {
	}
	
	public MemberHistoryDTO(Long membership) {
		this.membership = membership;
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((membership == null) ? 0 : membership.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberHistoryDTO other = (MemberHistoryDTO) obj;
		if (membership == null) {
			if (other.membership != null)
				return false;
		} else if (!membership.equals(other.membership))
			return false;
		return true;
	}

	public Map<String, String> getMemberTitle() {
		return memberTitle;
	}

	public void setMemberTitle(Map<String, String> memberTitle) {
		this.memberTitle = memberTitle;
	}
}
