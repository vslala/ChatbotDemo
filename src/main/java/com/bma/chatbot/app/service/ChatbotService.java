package com.bma.chatbot.app.service;

import org.springframework.stereotype.Service;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.factory.IntentFactory;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;

@Service
public class ChatbotService {

	public WebhookResponseVO executeIntentLogic(WebhookRequestVO webhookRequest) {
		IntentFactory factory = new IntentFactory();
		Intent intent = factory.getIntent(webhookRequest.getQueryResult().getIntent().getDisplayName());
		return intent.execute(webhookRequest);
	}

}
