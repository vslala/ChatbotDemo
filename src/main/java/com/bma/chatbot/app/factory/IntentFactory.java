package com.bma.chatbot.app.factory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.bma.chatbot.app.EnvironmentConfiguration;
import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.intents.GreetingCardIntent;
import com.bma.chatbot.app.intents.JokeIntent;
import com.bma.chatbot.app.intents.UserIntent;
import com.bma.chatbot.app.utils.ChatbotUtil;

public class IntentFactory {
	
	private static final Logger logger = Logger.getLogger(IntentFactory.class);
	
	public Intent getIntent(String intentDisplayName) {
		logger.debug("Intent Display Name: " + intentDisplayName);
		if (StringUtils.isEmpty(intentDisplayName)) {
			return null;
		}
		
		String intentPackage = EnvironmentConfiguration.getProperty("intent.package");
		String intentClassName = EnvironmentConfiguration.getProperty(intentDisplayName);
		Intent intent = null;
		if (! StringUtils.isEmpty(intentClassName)) {
			try {
				logger.info("Waking " + intentClassName + " from " + intentPackage);
				intent = (Intent) Class.forName(intentPackage + "." + intentClassName).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
			
		
		/*switch(intentDisplayName) {
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
		*/
		return intent;
	}
}
