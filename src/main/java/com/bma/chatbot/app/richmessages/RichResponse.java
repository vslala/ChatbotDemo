package com.bma.chatbot.app.richmessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bma.chatbot.app.contracts.RichMessage;

public class RichResponse {
	private List<Map<String, RichMessage>> items;
	private List<Suggestion> suggestions;
	
	public RichResponse() {
		items = new ArrayList<>();
	}

	public List<Map<String, RichMessage>> getItems() {
		return items;
	}

	public void addItem(RichMessage item) {
		items.add(new HashMap<String, RichMessage>(){
			{put(item.getName(), item);}
		});
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
}
