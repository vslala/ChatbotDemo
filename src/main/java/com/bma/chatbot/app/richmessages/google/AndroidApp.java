package com.bma.chatbot.app.richmessages.google;

import java.util.List;

public class AndroidApp {
	private String packageName;
	private List<VersionFilter> versions;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<VersionFilter> getVersions() {
		return versions;
	}
	public void setVersions(List<VersionFilter> versions) {
		this.versions = versions;
	}
	
	
}
