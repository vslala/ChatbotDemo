package com.bma.chatbot.app.payloads;

import com.bma.chatbot.app.enums.Platform;
import com.bma.chatbot.app.richresponses.RichResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GooglePayload {
	@JsonProperty
	private boolean expectUserResponse;
	@JsonProperty
	private RichResponse richResponse;
	
	public boolean isExpectUserResponse() {
		return expectUserResponse;
	}
	public void setExpectUserResponse(boolean expectUserResponse) {
		this.expectUserResponse = expectUserResponse;
	}
	public RichResponse getRichResponse() {
		return richResponse;
	}
	public void setRichResponse(RichResponse richResponse) {
		this.richResponse = richResponse;
	}
	
	@JsonIgnore
	public String getPlatformName() {
		return Platform.lowerCase(Platform.GOOGLE);
	}
	
}
