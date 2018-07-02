package com.bma.chatbot.app.richmessages.common;

import com.bma.chatbot.app.contracts.RichMessage;

public class Text implements RichMessage {
	private String[] text;

	public String[] getText() {
		return text;
	}

	public void setText(String[] text) {
		this.text = text;
	}

	@Override
	public String getName() {
		return "text";
	}
}
