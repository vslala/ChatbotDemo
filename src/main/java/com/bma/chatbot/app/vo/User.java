package com.bma.chatbot.app.vo;

import com.google.gson.annotations.SerializedName;

public class User {
	public class Payload {
		@SerializedName("lastSeen")
		private String lastSeen;
		@SerializedName("accessToken")
		private String accessToken;
		@SerializedName("locale")
		private String locale;
		@SerializedName("userId")
		private String userId;
		public String getLastSeen() {
			return lastSeen;
		}
		public void setLastSeen(String lastSeen) {
			this.lastSeen = lastSeen;
		}
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		public String getLocale() {
			return locale;
		}
		public void setLocale(String locale) {
			this.locale = locale;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	}
	private String id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
