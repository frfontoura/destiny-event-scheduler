package com.destinyEventScheduler.dto.bungie.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BungieAccountResponse {
	
	@JsonProperty("ThrottleSeconds")
	private String throttleSeconds;
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("ErrorStatus")
	private String errorStatus;

	@JsonProperty("Response")
	private BungieAccount bungieAccount;
	
	@JsonProperty("MessageData")
	private Object messageData;
	
	@JsonProperty("ErrorCode")
	private String errorCode;

	public String getThrottleSeconds() {
		return throttleSeconds;
	}

	public void setThrottleSeconds(String ThrottleSeconds) {
		this.throttleSeconds = ThrottleSeconds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String Message) {
		this.message = Message;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String ErrorStatus) {
		this.errorStatus = ErrorStatus;
	}

	public Object getMessageData() {
		return messageData;
	}

	public void setMessageData(Object MessageData) {
		this.messageData = MessageData;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String ErrorCode) {
		this.errorCode = ErrorCode;
	}

	@Override
	public String toString() {
		return "ClassPojo [ThrottleSeconds = " + throttleSeconds + ", Message = " + message + ", ErrorStatus = "
				+ errorStatus + ", Response = " + getBungieAccount() + ", MessageData = " + messageData + ", ErrorCode = "
				+ errorCode + "]";
	}

	public BungieAccount getBungieAccount() {
		return bungieAccount;
	}

	public void setBungieAccount(BungieAccount bungieAccount) {
		this.bungieAccount = bungieAccount;
	}
}