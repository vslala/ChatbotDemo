package com.bma.chatbot.app.vo;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Quotes {
	@SerializedName("ID")
	private int id;
	private String title;
	private String content;
	@SerializedName("custom_meta")
	private Map<String, String> customMeta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getCustomMeta() {
		return customMeta;
	}
	public void setCustomMeta(Map<String, String> customMeta) {
		this.customMeta = customMeta;
	}
	
	
}
