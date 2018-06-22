package com.bma.chatbot.app.intents;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bma.chatbot.app.contracts.Intent;
import com.bma.chatbot.app.utils.ChatbotUtil;
import com.bma.chatbot.app.vo.JokeVO;
import com.bma.chatbot.app.vo.WebhookRequestVO;
import com.bma.chatbot.app.vo.WebhookResponseVO;
import com.google.gson.reflect.TypeToken;

public class JokeIntent implements Intent {

	private static final Logger logger = Logger.getLogger(JokeIntent.class);
	private static final String API_ENDPPOINT = "https://api.chucknorris.io/jokes/random";
	
	@Override
	public WebhookResponseVO execute(WebhookRequestVO webhookRequest) {
		WebhookResponseVO webhookResponse= null;
		String actionName = webhookRequest.getQueryResult().getAction();
		if (! StringUtils.isEmpty(actionName)) {
			switch (actionName) {
				case "tell_joke":
					webhookResponse = this.tellJoke(webhookRequest);
					break;
				case "another_action":
					
					break;
				default: 
					webhookResponse = defaultResponse(webhookRequest);
					break;
			}
		}
		return webhookResponse;
	}

	/**
	 * Give Service call to Joke API and return a Joke
	 * @param webhookRequest
	 * @return
	 */
	private WebhookResponseVO tellJoke(WebhookRequestVO webhookRequest) {
		HttpHeaders headers = buildHeadersForJokeApi();
		
		UriComponentsBuilder builder = buildRequestStringComponent(webhookRequest);
		
		HttpEntity<String> requestEntity = new HttpEntity<String>("parameters", headers);
		
		logger.info("Service Endpoint: " + builder.buildAndExpand(new HashMap<>()).toUriString());
		ResponseEntity<JokeVO> responseEntity = null;
		responseEntity = makeServiceCall(builder, requestEntity, responseEntity);
		if (! ChatbotUtil.isSet(responseEntity)) {
			return createServiceFailureResponse();
		}
		JokeVO joke = responseEntity.getBody();
		
		WebhookResponseVO responseVO = new WebhookResponseVO();
		responseVO.setFulfillmentText(joke.getValue());
		
		return responseVO;
	}

	private UriComponentsBuilder buildRequestStringComponent(WebhookRequestVO webhookRequest) {
		String categories = printKeywordsSeparateBy(webhookRequest.getQueryResult().getParameters().get("category"), ",");
		UriComponentsBuilder builder = null;
		if (categories.isEmpty()) {
			builder = UriComponentsBuilder.fromUriString(API_ENDPPOINT);
		} else {
			builder = UriComponentsBuilder.fromUriString(API_ENDPPOINT)
			.queryParam("category", categories);
		}
		return builder;
	}

	private WebhookResponseVO createServiceFailureResponse() {
		WebhookResponseVO responseVO = new WebhookResponseVO();
		responseVO.setFulfillmentText("The backend Jokes API is not accessible at the moment. Please check back later.");
		return responseVO;
	}

	private ResponseEntity<JokeVO> makeServiceCall(UriComponentsBuilder builder, HttpEntity<String> requestEntity,
			ResponseEntity<JokeVO> responseEntity) {
		try {
			RestTemplate rest = new RestTemplate();
			responseEntity = rest.exchange((builder.buildAndExpand(new HashMap<>())).toUri(), HttpMethod.GET, requestEntity, JokeVO.class);
		} catch(HttpClientErrorException e) {
			logger.warn("Service call failed.");
		}
		return responseEntity;
	}

	private HttpHeaders buildHeadersForJokeApi() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
		return headers;
	}

	private String printKeywordsSeparateBy(Object object, String separator) {
		List<String> keywords = (List<String>) object;
		if (! ChatbotUtil.isSet(keywords) || keywords.isEmpty()) {
			return "";
		}
		String phrase = "";
		for (String s: keywords) {
			phrase += s + separator;
		}
		return phrase.substring(0, phrase.length()-1);
	}

}
