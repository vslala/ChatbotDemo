package com.bma.chatbot.app.richmessages;

import com.bma.chatbot.app.contracts.RichMessage;

public class Suggestion implements RichMessage {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getName() {
		return "suggestion";
	}
}
