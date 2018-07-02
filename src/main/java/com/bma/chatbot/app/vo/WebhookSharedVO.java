package com.bma.chatbot.app.vo;

import java.util.List;
import java.util.Map;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.Platform;
import com.bma.chatbot.app.richmessages.common.BasicCard;
import com.bma.chatbot.app.richmessages.common.Card;
import com.bma.chatbot.app.richmessages.common.CarouselSelect;
import com.bma.chatbot.app.richmessages.common.Image;
import com.bma.chatbot.app.richmessages.common.LinkOutSuggestion;
import com.bma.chatbot.app.richmessages.common.ListSelect;
import com.bma.chatbot.app.richmessages.common.QuickReplies;
import com.bma.chatbot.app.richmessages.common.SimpleResponses;
import com.bma.chatbot.app.richmessages.common.Suggestion;
import com.bma.chatbot.app.richmessages.common.Text;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebhookSharedVO {

	public class EventInput {
		private String name;
		private Map<String, Object> parameters;
		private String languageCode;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Map<String, Object> getParameters() {
			return parameters;
		}

		public void setParameters(Map<String, Object> parameters) {
			this.parameters = parameters;
		}

		public String getLanguageCode() {
			return languageCode;
		}

		public void setLanguageCode(String languageCode) {
			this.languageCode = languageCode;
		}

	}

	public class Context {
		private String name;
		private int lifespanCount;
		private Map<String, Object> parameters;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getLifespanCount() {
			return lifespanCount;
		}

		public void setLifespanCount(int lifespanCount) {
			this.lifespanCount = lifespanCount;
		}

		public Map<String, Object> getParameters() {
			return parameters;
		}

		public void setParameters(Map<String, Object> parameters) {
			this.parameters = parameters;
		}

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public class Message {
		private Platform platform;
		private Text text;
		private Image image;
		private QuickReplies quickReplies;
		private Card card;
		private SimpleResponses simpleResponses;
		private BasicCard basicCard;
		private List<Suggestion> suggestions;
		private LinkOutSuggestion linkOutSuggestion;
		private ListSelect listSelect;
		private CarouselSelect carouselSelect;

		public Platform getPlatform() {
			return platform;
		}

		public void setPlatform(Platform platform) {
			this.platform = platform;
		}

		public Text getText() {
			return text;
		}

		public void setText(Text text) {
			this.text = text;
		}

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}

		public QuickReplies getQuickReplies() {
			return quickReplies;
		}

		public void setQuickReplies(QuickReplies quickReplies) {
			this.quickReplies = quickReplies;
		}

		public Card getCard() {
			return card;
		}

		public void setCard(Card card) {
			this.card = card;
		}

		public BasicCard getBasicCard() {
			return basicCard;
		}

		public void setBasicCard(BasicCard basicCard) {
			this.basicCard = basicCard;
		}

		public List<Suggestion> getSuggestions() {
			return suggestions;
		}

		public void setSuggestions(List<Suggestion> suggestions) {
			this.suggestions = suggestions;
		}

		public LinkOutSuggestion getLinkOutSuggestion() {
			return linkOutSuggestion;
		}

		public void setLinkOutSuggestion(LinkOutSuggestion linkOutSuggestion) {
			this.linkOutSuggestion = linkOutSuggestion;
		}

		public ListSelect getListSelect() {
			return listSelect;
		}

		public void setListSelect(ListSelect listSelect) {
			this.listSelect = listSelect;
		}

		public CarouselSelect getCarouselSelect() {
			return carouselSelect;
		}

		public void setCarouselSelect(CarouselSelect carouselSelect) {
			this.carouselSelect = carouselSelect;
		}

		public SimpleResponses getSimpleResponses() {
			return simpleResponses;
		}

		public void setSimpleResponses(SimpleResponses simpleResponses) {
			this.simpleResponses = simpleResponses;
		}

		
	}

}
