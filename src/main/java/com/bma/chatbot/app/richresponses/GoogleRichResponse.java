package com.bma.chatbot.app.richresponses;

public class GoogleRichResponse {
	private RichResponse richResponse;
	private boolean expectUserResponse;
	
	public RichResponse getRichResponse() {
		return richResponse;
	}
	public void setRichResponse(RichResponse richResponse) {
		this.richResponse = richResponse;
	}
	public boolean isExpectUserResponse() {
		return expectUserResponse;
	}
	public void setExpectUserResponse(boolean expectUserResponse) {
		this.expectUserResponse = expectUserResponse;
	}
	
	
}
