package com.bma.chatbot.app.richmessages.common;

import com.bma.chatbot.app.contracts.RichMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Suggestion implements RichMessage {
	@JsonProperty
	private String title;
	
	public Suggestion() {}

	public Suggestion(String title) {
		this.title = title;
	}

	@Override
	public String getName() {
		return "suggestion";
	}
}
