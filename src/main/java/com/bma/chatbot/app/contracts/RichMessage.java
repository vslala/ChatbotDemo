package com.bma.chatbot.app.contracts;

import com.bma.chatbot.app.enums.Platform;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

public interface RichMessage {
	@JsonIgnore
	public String getName();
	
}
