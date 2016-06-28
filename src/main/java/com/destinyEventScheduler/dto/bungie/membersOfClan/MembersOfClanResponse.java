package com.destinyEventScheduler.dto.bungie.membersOfClan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MembersOfClanResponse {
	
	@JsonProperty("ThrottleSeconds")
	private String throttleSeconds;
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("ErrorStatus")
	private String errorStatus;
	
	@JsonProperty("Response")
	private Response response;
	
	@JsonProperty("MessageData")
	private Object messageData;
	
	@JsonProperty("ErrorCode")
	private String errorCode;

	public String getThrottleSeconds() {
		return throttleSeconds;
	}

	public void setThrottleSeconds(String throttleSeconds) {
		this.throttleSeconds = throttleSeconds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Object getMessageData() {
		return messageData;
	}

	public void setMessageData(Object messageData) {
		this.messageData = messageData;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



}