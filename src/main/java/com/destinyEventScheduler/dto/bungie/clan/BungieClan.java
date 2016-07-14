package com.destinyEventScheduler.dto.bungie.clan;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BungieClan {

	@JsonProperty("Response")
	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}