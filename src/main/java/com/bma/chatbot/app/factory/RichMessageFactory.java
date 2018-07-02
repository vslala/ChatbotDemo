package com.bma.chatbot.app.factory;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.RichMessageType;
import com.bma.chatbot.app.richmessages.common.BasicCard;
import com.bma.chatbot.app.richmessages.common.Card;
import com.bma.chatbot.app.richmessages.common.CarouselSelect;
import com.bma.chatbot.app.richmessages.common.Image;
import com.bma.chatbot.app.richmessages.common.LinkOutSuggestion;
import com.bma.chatbot.app.richmessages.common.ListSelect;
import com.bma.chatbot.app.richmessages.common.QuickReplies;
import com.bma.chatbot.app.richmessages.common.SimpleResponse;
import com.bma.chatbot.app.richmessages.common.SimpleResponses;
import com.bma.chatbot.app.richmessages.common.Suggestion;
import com.bma.chatbot.app.richmessages.common.Text;
import com.bma.chatbot.app.richmessages.google.BasicCardGoogle;
import com.bma.chatbot.app.richmessages.google.OpenUrlAction;
import com.bma.chatbot.app.utils.ChatbotUtil;

public class RichMessageFactory {
	
	public static com.bma.chatbot.app.richmessages.google.LinkOutSuggestion getLinkOutSuggestionForGoogle(
			String destinationName, String url, OpenUrlAction openUrlAction
			) {
		return new com.bma.chatbot.app.richmessages.google.LinkOutSuggestion.Builder()
				.destinationName(destinationName)
				.url(url)
				.openUrlAction(openUrlAction)
				.build();
	}
	
	public static <T> T getRichMessage(RichMessageType messageType, Class<T> classType) {
		RichMessage richMessage = null;
		switch (messageType) {
		case TEXT:
			richMessage = new Text();
			break;
		case IMAGE:
			richMessage = new Image();
			break;
		case QUICK_REPLIES:
			richMessage = new QuickReplies();
			break;
		case CARD:
			richMessage = new Card();
			break;
		case BASIC_CARD:
			richMessage = new BasicCard();
			break;
		case SIMPLE_RESPONSE:
			richMessage = new SimpleResponse();
			break;
		case SIMPLE_RESPONSES:
			richMessage = new SimpleResponses();
			break;
		case SUGGESTION:
			richMessage = new Suggestion();
			break;
		case LINK_OUT_SUGGESTION:
			richMessage = new LinkOutSuggestion();
			break;
		case LIST_SELECT:
			richMessage = new ListSelect();
			break;
		case CAROUSEL_SELECT:
			richMessage = new CarouselSelect();
			break;
		default:

			break;

		}
		T target = classType.cast(richMessage);
		return target;
	}


}
