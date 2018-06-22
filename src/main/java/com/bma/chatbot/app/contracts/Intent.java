package com.bma.chatbot.app.contracts;

import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;

public interface Intent {
	public WebhookResponseVO execute(WebhookRequestVO webhookRequest);
	default public WebhookResponseVO defaultResponse(WebhookRequestVO webhookRequest) {
		WebhookResponseVO webhookResponse = new WebhookResponseVO();
		webhookResponse.setFulfillmentText("Sorry, we do not recognize your action");
		return webhookResponse;
	}
}
