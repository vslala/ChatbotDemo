package com.bma.chatbot.app.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.factory.IntentFactory;
import com.bma.chatbot.app.service.ChatbotService;
import com.bma.chatbot.app.utils.ChatbotUtil;
import com.bma.chatbot.app.vo.FulfillmentResponse;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;

@RestController
@RequestMapping("/chatbot")
public class ChatbotApi {

	private static final Logger logger = Logger.getLogger(ChatbotApi.class);

	@Autowired
	private ChatbotService chatbotService;
	
	@PostMapping("/api")
	public WebhookResponseVO webhook(@RequestBody String webhookRequestBody) {

		WebhookRequestVO webhookRequest = (WebhookRequestVO) ChatbotUtil.convertJSONToObj(webhookRequestBody,
				WebhookRequestVO.class);
		logger.debug(ChatbotUtil.printObj(webhookRequest));

		WebhookResponseVO webhookResponse = chatbotService.executeIntentLogic(webhookRequest);

		return webhookResponse;
	}

}
