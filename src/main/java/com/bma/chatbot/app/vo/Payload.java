package com.bma.chatbot.app.vo;

import com.bma.chatbot.app.payloads.FacebookPayload;
import com.bma.chatbot.app.payloads.GooglePayload;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {
	
	@SerializedName("google")
	private GooglePayload googlePayload;
	
	@SerializedName("facebook")
	private FacebookPayload facebookPayload;

	public GooglePayload getGooglePayload() {
		return googlePayload;
	}

	public void setGooglePayload(GooglePayload googlePayload) {
		this.googlePayload = googlePayload;
	}

	public FacebookPayload getFacebookPayload() {
		return facebookPayload;
	}

	public void setFacebookPayload(FacebookPayload facebookPayload) {
		this.facebookPayload = facebookPayload;
	}
	

}
