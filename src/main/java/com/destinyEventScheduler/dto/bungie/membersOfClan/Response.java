package com.destinyEventScheduler.dto.bungie.membersOfClan;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("results")
	private List<MemberBungie> members;
	private String totalResults;
	private Query query;
	private String useTotalResults;
	private boolean hasMore;

	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public List<MemberBungie> getMembers() {
		return members;
	}

	public void setMembers(List<MemberBungie> members) {
		this.members = members;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getUseTotalResults() {
		return useTotalResults;
	}

	public void setUseTotalResults(String useTotalResults) {
		this.useTotalResults = useTotalResults;
	}

}