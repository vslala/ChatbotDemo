package com.bma.chatbot.app.intents;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.utils.ChatbotConstants;
import com.bma.chatbot.app.utils.ChatbotUtil;
import com.bma.chatbot.app.vo.User;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;
import com.google.gson.JsonElement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class UserIntent implements Intent {

	private static final Logger logger = Logger.getLogger(UserIntent.class);
	
	@Override
	public WebhookResponseVO execute(WebhookRequestVO webhookRequest) {
		String actionName = webhookRequest.getQueryResult().getAction();
		WebhookResponseVO webhookResponse = null;
		switch(actionName) {
		case "fetch_user_name":
			webhookResponse = this.getUserName(webhookRequest);
			break;
		}
		return webhookResponse;
	}

	private WebhookResponseVO getUserName(WebhookRequestVO webhookRequest) {
		String userPayloadStr = webhookRequest.getOriginalDetectIntentRequest().getPayload().get("user").toString();
		logger.debug(ChatbotUtil.printObj(userPayloadStr));
		User.Payload userPayload = (User.Payload)ChatbotUtil.convertJSONToObj(userPayloadStr, User.Payload.class);
		
		
		String jwt = userPayload.getAccessToken();
		Map<String, Object> parameters = webhookRequest.getQueryResult().getParameters();
		
		String nameType = parameters.get("name-type").toString();
		User user = (User) readJwtToken(jwt, User.class);
		String username = fetchNameFromUserToken(nameType, user);
		
		WebhookResponseVO webhookResponse = new WebhookResponseVO();
		webhookResponse.setFulfillmentText("Your " + nameType + "name is " + username);
		return webhookResponse;
	}

	private String fetchNameFromUserToken(String namePart, User user) {
		String name = "";
		if (! StringUtils.isEmpty(namePart)) {
			if (namePart.toUpperCase().contains("FIRST")) {
				name = user.getFirstName();
			} else if (namePart.toUpperCase().contains("LAST")) {
				name = user.getLastName();
			} else {
				name = user.getFirstName().concat(" ").concat(user.getLastName());
			}
		} else {
			name = user.getFirstName().concat(" ").concat(user.getLastName());
		}
		return name;
	}

	private Object readJwtToken(String jwt, Class clazz) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(ChatbotConstants.SECRET_KEY.getBytes())
				.parseClaimsJws(jwt);
		logger.debug("Request Token Parsed Successfully.\n" + claims.getBody().get("data").toString());
		return ChatbotUtil.convertJSONToObj(claims.getBody().get("data").toString(), clazz);
	}

}
