package com.bma.chatbot.app.factory;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.intents.GreetingCardIntent;
import com.bma.chatbot.app.intents.JokeIntent;
import com.bma.chatbot.app.intents.UserIntent;

public class IntentFactory {
	
	private static final Logger logger = Logger.getLogger(IntentFactory.class);
	
	public Intent getIntent(String intentDisplayName) {
		logger.debug("Intent Display Name: " + intentDisplayName);
		if (StringUtils.isEmpty(intentDisplayName)) {
			return null;
		}
		
		Intent intent = null;
		
		switch(intentDisplayName) {
		case "humor.joke":
		case "humor.joke.followup":
			intent = new JokeIntent();
			break;
		case "user.name":
			intent = new UserIntent();
			break;
		case "greeting.card":
			intent = new GreetingCardIntent();
			break;
		default:
			logger.info("Intent mapping not defined.");
			break;
		}
		
		return intent;
	}
}
