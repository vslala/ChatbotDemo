package com.bma.chatbot.app.richmessages.common;

import com.bma.chatbot.app.contracts.RichMessage;

public class SimpleResponse implements RichMessage {
	private String textToSpeech;
	private String ssml;
	private String displayText;

	public String getTextToSpeech() {
		return textToSpeech;
	}

	public void setTextToSpeech(String textToSpeech) {
		this.textToSpeech = textToSpeech;
	}

	public String getSsml() {
		return ssml;
	}

	public void setSsml(String ssml) {
		this.ssml = ssml;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@Override
	public String getName() {
		return "simpleResponse";
	}

}