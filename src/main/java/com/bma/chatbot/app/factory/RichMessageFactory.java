package com.bma.chatbot.app.factory;

import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.RichMessageType;
import com.bma.chatbot.app.richmessages.BasicCard;
import com.bma.chatbot.app.richmessages.Card;
import com.bma.chatbot.app.richmessages.CarouselSelect;
import com.bma.chatbot.app.richmessages.Image;
import com.bma.chatbot.app.richmessages.LinkOutSuggestion;
import com.bma.chatbot.app.richmessages.ListSelect;
import com.bma.chatbot.app.richmessages.QuickReplies;
import com.bma.chatbot.app.richmessages.SimpleResponse;
import com.bma.chatbot.app.richmessages.SimpleResponses;
import com.bma.chatbot.app.richmessages.Suggestion;
import com.bma.chatbot.app.richmessages.Text;

public class RichMessageFactory {

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
