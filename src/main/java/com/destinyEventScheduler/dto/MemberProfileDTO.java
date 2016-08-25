package com.destinyEventScheduler.dto;

import java.util.List;

import com.destinyEventScheduler.model.Member;

public class MemberProfileDTO {

	private Member member;
	private int evaluationsMade;
	private List<PlayedTypeDTO> playedTypes;
	private FavoriteEventDTO favoriteEvent;
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getEvaluationsMade() {
		return evaluationsMade;
	}

	public void setEvaluationsMade(int evaluationsMade) {
		this.evaluationsMade = evaluationsMade;
	}

	public List<PlayedTypeDTO> getPlayedTypes() {
		return playedTypes;
	}

	public void setPlayedTypes(List<PlayedTypeDTO> playedTypes) {
		this.playedTypes = playedTypes;
	}

	public FavoriteEventDTO getFavoriteEvent() {
		return favoriteEvent;
	}

	public void setFavoriteEvent(FavoriteEventDTO event) {
		this.favoriteEvent = event;
	}

}
