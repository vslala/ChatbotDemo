package com.bma.chatbot.app.richmessages;

import java.util.List;

import com.bma.chatbot.app.contracts.RichMessage;

public class CarouselSelect implements RichMessage {
	private List<ListSelect.Item> items;

	public List<ListSelect.Item> getItems() {
		return items;
	}

	public void setItems(List<ListSelect.Item> items) {
		this.items = items;
	}

	@Override
	public String getName() {
		return "carouselSelect";
	}
}
