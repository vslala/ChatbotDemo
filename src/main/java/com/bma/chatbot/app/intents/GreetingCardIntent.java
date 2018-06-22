package com.bma.chatbot.app.intents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.contracts.RichMessage;
import com.bma.chatbot.app.enums.Platform;
import com.bma.chatbot.app.enums.RichMessageType;
import com.bma.chatbot.app.factory.RichMessageFactory;
import com.bma.chatbot.app.payloads.GooglePayload;
import com.bma.chatbot.app.richmessages.BasicCard;
import com.bma.chatbot.app.richmessages.BasicCard.Button;
import com.bma.chatbot.app.richmessages.BasicCard.Button.OpenUriAction;
import com.bma.chatbot.app.richmessages.google.BasicCardGoogle;
import com.bma.chatbot.app.richmessages.Image;
import com.bma.chatbot.app.richmessages.RichResponse;
import com.bma.chatbot.app.richmessages.SimpleResponse;
import com.bma.chatbot.app.richmessages.SimpleResponses;
import com.bma.chatbot.app.richresponses.GoogleRichResponse;
import com.bma.chatbot.app.utils.ChatbotUtil;
import com.bma.chatbot.app.vo.Payload;
import com.bma.chatbot.app.vo.Quotes;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;
import com.bma.chatbot.app.vo.WebhookSharedVO;
import com.bma.chatbot.app.vo.WebhookSharedVO.Message;

public class GreetingCardIntent implements Intent {
	
	private static final Logger logger = Logger.getLogger(GreetingCardIntent.class);

	private static final String QUOTES_API = "https://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=5";
	
	@Override
	public WebhookResponseVO execute(WebhookRequestVO webhookRequest) {
		String actionName = webhookRequest.getQueryResult().getAction();
		WebhookResponseVO webhookResponse = null;
		if (StringUtils.isEmpty(actionName)) {
			webhookResponse = this.defaultAction(webhookRequest);
		} else {
			
		}
		return webhookResponse;
	}

	private WebhookResponseVO defaultAction(WebhookRequestVO webhookRequest) {
		RestTemplate rest = new RestTemplate();
		String quotesJSON = rest.getForObject(QUOTES_API, String.class);
		logger.debug(quotesJSON);
		Quotes[] quotes = (Quotes[]) ChatbotUtil.convertJSONToObj(quotesJSON, Quotes[].class);
		WebhookResponseVO webhookResponse = new WebhookResponseVO();
		if (ChatbotUtil.isSet(quotes) && quotes.length != 0) {
			String speech = quotes[0].getContent().replaceAll("\\<.*?>","");
			webhookResponse.setFulfillmentText(speech);
			
			List<Message> fulfillmentMessages = buildFulfillmentMessages(speech);
			
			Map<String, Object> payload = buildPayload(speech);
			
			webhookResponse.setFulfillmentMessages(fulfillmentMessages);
			webhookResponse.setPayload(payload);
			
		}
		return webhookResponse;
	}

	private List<Message> buildFulfillmentMessages(String speech) {
		List<Message> fulfillmentMessages = new ArrayList<>();
		Message message = new WebhookSharedVO().new Message();
		message.setBasicCard(buildBasicCard(speech));
//		message.setPlatform(Platform.ACTIONS_ON_GOOGLE);
		fulfillmentMessages.add(message );
		return fulfillmentMessages;
	}

	private Map<String, Object> buildPayload(String speech) {
		Map<String, Object> payload = new HashMap<>();
		
		RichResponse richResponse = new RichResponse();
		richResponse.addItem(buildSimpleResponse(speech));
		richResponse.addItem(buildGoogleBasicCard(speech));
		
		GooglePayload googlePayload = new GooglePayload();
		googlePayload.setExpectUserResponse(true);
		googlePayload.setRichResponse(richResponse);
		payload.put(Platform.lowerCase(Platform.GOOGLE), googlePayload);
		return payload;
	}

	private RichMessage buildGoogleBasicCard(String speech) {
		BasicCardGoogle basicCard = new BasicCardGoogle.Builder()
				.title("Greeting Card")
				.subtitle("Quote of the Day")
				.formattedText(speech)
				.image(new com.bma.chatbot.app.richmessages.google.Image(
						"https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg",
						speech,
						200,
						300))
				.addButton(new com.bma.chatbot.app.richmessages.google.Button.Builder()
						.title("Greeting!")
						.url("https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg")
						.build()
						)
				.build();
		return basicCard;
	}

	private BasicCard buildBasicCard(String speech) {
		BasicCard basicCard = RichMessageFactory.getRichMessage(RichMessageType.BASIC_CARD, BasicCard.class);
		basicCard.setTitle("Greeting Card");
		basicCard.setSubtitle(speech);
		basicCard.setFormattedText(speech);
		Image image = new Image();
		image.setAccessibilityText("DZone Logo");
		image.setImageUri("https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg");
		basicCard.setImage(image);
		List<Button> buttons = new ArrayList<>();
		Button button = basicCard.new Button();
		OpenUriAction uri = button.new OpenUriAction();
		uri.setUri("https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg");
		button.setOpenUriAction(uri);
		button.setTitle("Greeting Card Link");
		buttons.add(button);
		basicCard.setButtons(buttons);
		return basicCard;
	}

	private SimpleResponse buildSimpleResponse(String speech) {
		SimpleResponse simpleResponse = RichMessageFactory.getRichMessage(RichMessageType.SIMPLE_RESPONSE, SimpleResponse.class);
		simpleResponse.setDisplayText(speech);
		simpleResponse.setSsml("<speak>" + speech + "</speak>");
		return simpleResponse;
	}

}
