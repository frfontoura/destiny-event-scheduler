package com.destinyEventScheduler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayedTypeDTO {

	private Long eventTypeId;
	private String eventTypeName;
	private Long timesPlayed;
	
	@JsonProperty("en")
	private String eventTypeNameEn;
	
	@JsonProperty("pt")
	private String eventTypeNamePt;
	
	@JsonProperty("es")
	private String eventTypeNameEs;

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

	public String getEventTypeNameEn() {
		return eventTypeNameEn;
	}

	public void setEventTypeNameEn(String eventTypeNameEn) {
		this.eventTypeNameEn = eventTypeNameEn;
	}

	public String getEventTypeNamePt() {
		return eventTypeNamePt;
	}

	public void setEventTypeNamePt(String eventTypeNamePt) {
		this.eventTypeNamePt = eventTypeNamePt;
	}

	public String getEventTypeNameEs() {
		return eventTypeNameEs;
	}

	public void setEventTypeNameEs(String eventTypeNameEs) {
		this.eventTypeNameEs = eventTypeNameEs;
	}

}
