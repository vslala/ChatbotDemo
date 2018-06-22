package com.bma.chatbot.playground;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.RichMessageType;
import com.bma.chatbot.app.factory.RichMessageFactory;
import com.bma.chatbot.app.richmessages.LinkOutSuggestion;
import com.bma.chatbot.app.richmessages.SimpleResponse;
import com.bma.chatbot.app.utils.ChatbotUtil;

public class TestPlayground {
	
	@Test
	public void createJson() {
		Map<String, RichMessage> richMessages = new HashMap<String, RichMessage>();
		RichMessageFactory factory = new RichMessageFactory();
		LinkOutSuggestion linkOutSuggestion = factory.getRichMessage(RichMessageType.LINK_OUT_SUGGESTION, LinkOutSuggestion.class);
		linkOutSuggestion.setDestinationName("BeMyAficionado");
		linkOutSuggestion.setUri("https://www.bemyaficionado.com");
		
		SimpleResponse simpleResponse = factory.getRichMessage(RichMessageType.SIMPLE_RESPONSE, SimpleResponse.class);
		simpleResponse.setTextToSpeech("This is a simple text to speech.");
		
		richMessages.put(RichMessageType.getLinkOutSuggestionText(), linkOutSuggestion);
		richMessages.put(RichMessageType.getSimpleResponseText(), simpleResponse);
		
		System.out.println(ChatbotUtil.convertObjectToJSON(richMessages));
	}
}
