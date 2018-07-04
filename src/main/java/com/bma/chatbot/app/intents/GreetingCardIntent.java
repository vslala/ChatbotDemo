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
import com.bma.chatbot.app.factory.CommonMessageFactory;
import com.bma.chatbot.app.factory.GoogleMessageFactory;
import com.bma.chatbot.app.factory.RichMessageFactory;
import com.bma.chatbot.app.payloads.GooglePayload;
import com.bma.chatbot.app.richmessages.common.BasicCard;
import com.bma.chatbot.app.richmessages.common.Image;
import com.bma.chatbot.app.richmessages.common.SimpleResponse;
import com.bma.chatbot.app.richmessages.common.Suggestion;
import com.bma.chatbot.app.richmessages.google.BasicCardGoogle;
import com.bma.chatbot.app.richmessages.google.OpenUrlAction;
import com.bma.chatbot.app.richresponses.RichResponse;
import com.bma.chatbot.app.utils.ChatbotUtil;
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
		fulfillmentMessages.add(message);
		return fulfillmentMessages;
	}

	private Map<String, Object> buildPayload(String speech) {
		Map<String, Object> payload = new HashMap<>();
		
		RichResponse richResponse = new RichResponse();
		richResponse.addItem(buildSimpleResponse(speech));
		richResponse.addItem(buildGoogleBasicCard(speech));
		richResponse.addSuggestion(new Suggestion("Tell me a joke"));
		richResponse.addLinkOutSuggestion((GoogleMessageFactory.createLinkOutSuggestion(
				"Google", 
				"https://www.google.com", 
				new OpenUrlAction("https://www.google.com"))));
		
		GooglePayload googlePayload = new GooglePayload();
		googlePayload.setExpectUserResponse(true);
		googlePayload.setRichResponse(richResponse);
		payload.put(googlePayload.getPlatformName(), googlePayload);
		return payload;
	}

	private RichMessage buildGoogleBasicCard(String speech) {
		return GoogleMessageFactory.createBasicCard(
				"Greeting Card",
				"Quote of the Day",
				speech,
				GoogleMessageFactory.createImage(
						"https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg",
						speech,
						200,
						300
						),
				GoogleMessageFactory.createButton(
						"Greeting!",
						"https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg"
						)
				); 
	}

	private BasicCard buildBasicCard(String speech) {
		BasicCard basicCard = CommonMessageFactory.createBasicCard(
				"Greeting Card",
				speech,
				speech,
				CommonMessageFactory.createImage(
						"DZone Logo", 
						"https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg"),
				CommonMessageFactory.createButton(
						"Greeting Card Link",
						"https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg")
				);
		return basicCard;
	}

	private SimpleResponse buildSimpleResponse(String speech) {
		return CommonMessageFactory.createSimpleResponseWithSSML(
				"<speak>" + speech + "</speak>", 
				speech);
	}

}
