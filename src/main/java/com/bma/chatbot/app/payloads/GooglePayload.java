package com.bma.chatbot.app.payloads;

import com.bma.chatbot.app.richresponses.RichResponse;

public class GooglePayload {
	private boolean expectUserResponse;
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
	
}
