package com.bma.chatbot.app.richmessages;

import java.util.List;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.Platform;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class BasicCard implements RichMessage {
	private String title;
	private String subtitle;
	private String formattedText;
	private Image image;
	private List<Button> buttons;

	public class Button {
		private String title;
		private OpenUriAction openUriAction;

		public class OpenUriAction {
			private String uri;

			public String getUri() {
				return uri;
			}

			public void setUri(String uri) {
				this.uri = uri;
			}
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public OpenUriAction getOpenUriAction() {
			return openUriAction;
		}

		public void setOpenUriAction(OpenUriAction openUriAction) {
			this.openUriAction = openUriAction;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getFormattedText() {
		return formattedText;
	}

	public void setFormattedText(String formattedText) {
		this.formattedText = formattedText;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String getName() {
		return "basicCard";
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}


}

