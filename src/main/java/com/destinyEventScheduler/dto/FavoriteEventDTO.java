package com.destinyEventScheduler.dto;

import com.destinyEventScheduler.model.Event;

public class FavoriteEventDTO {

	private Long timesPlayed;
	private Event event;

	public Long getTimesPlayed() {
		return timesPlayed;
	}

	public void setTimesPlayed(Long timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
