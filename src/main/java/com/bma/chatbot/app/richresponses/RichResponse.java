package com.bma.chatbot.app.richresponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.richmessages.common.Suggestion;
import com.bma.chatbot.app.richmessages.google.LinkOutSuggestion;

public class RichResponse {
	private List<Map<String, RichMessage>> items;
	private List<Suggestion> suggestions;
	private LinkOutSuggestion linkOutSuggestion;
	
	public RichResponse() {
		items = new ArrayList<>();
		suggestions = new ArrayList<>();
	}

	public List<Map<String, RichMessage>> getItems() {
		return items;
	}

	public void addItem(RichMessage item) {
		this.items.add(new HashMap<String, RichMessage>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{put(item.getName(), item);}
		});
	}
	
	public void addSuggestion(Suggestion suggestion) {
		this.suggestions.add(suggestion);
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public LinkOutSuggestion getLinkOutSuggestion() {
		return linkOutSuggestion;
	}

	public void addLinkOutSuggestion(LinkOutSuggestion linkOutSuggestion) {
		this.linkOutSuggestion = linkOutSuggestion;
	}
}
