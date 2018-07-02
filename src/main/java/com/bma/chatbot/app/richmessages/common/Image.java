package com.bma.chatbot.app.richmessages.common;

import com.bma.chatbot.app.contracts.RichMessage;
import com.google.gson.annotations.SerializedName;

public class Image implements RichMessage {
	private String imageUri;
	private String accessibilityText;
	
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public String getAccessibilityText() {
		return accessibilityText;
	}
	public void setAccessibilityText(String accessibilityText) {
		this.accessibilityText = accessibilityText;
	}
	@Override
	public String getName() {
		return "image";
	}
	
}
