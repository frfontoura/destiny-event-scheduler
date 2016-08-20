package com.destinyEventScheduler.dto;

public class PlayedTypeDTO {

	private Long eventTypeId;
	private String eventTypeName;
	private Long timesPlayed;

	public Long getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public Long getTimesPlayed() {
		return timesPlayed;
	}

	public void setTimesPlayed(Long timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

}
