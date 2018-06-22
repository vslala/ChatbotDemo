package com.bma.chatbot.app.richmessages;

import java.util.List;

import com.bma.chatbot.app.contracts.RichMessage;

public class ListSelect implements RichMessage {
	private String title;
	private List<Item> items;

	public class Item {
		private SelectItemInfo info;
		private String title;
		private String description;
		private Image image;

		public class SelectItemInfo {
			private String key;
			private String[] synonyms;

			public String getKey() {
				return key;
			}

			public void setKey(String key) {
				this.key = key;
			}

			public String[] getSynonyms() {
				return synonyms;
			}

			public void setSynonyms(String[] synonyms) {
				this.synonyms = synonyms;
			}

		}

		public SelectItemInfo getInfo() {
			return info;
		}

		public void setInfo(SelectItemInfo info) {
			this.info = info;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String getName() {
		return "listSelect";
	}
}

