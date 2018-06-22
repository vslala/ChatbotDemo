package com.bma.chatbot.app.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bma.chatbot.app.vo.View;

@Controller
@RequestMapping("/api")
public class Auth {
	
	private static final Logger logger = Logger.getLogger(Auth.class);
	private static final String ACCESS_TOKEN = "simpleaccesstoken";
	private static final String PROJECT_ID  = "be-my-aficionado-1491969540599";
	private static final String OAUTH_REDIRECT = "#access_token=%s&token_type=bearer&state=%s";
	
	
	@GetMapping("/auth")
	public ModelAndView auth(@RequestParam(value="client_id", defaultValue="") String clientId, 
			@RequestParam(value="redirect_uri", defaultValue="") String redirectUri,  
			@RequestParam(value="state", defaultValue="") String state,
			@RequestParam(value="response_type", defaultValue="") String resType) {
		StringBuilder sb = new StringBuilder("Query Params:\n")
				.append(clientId).append("\n")
				.append(redirectUri).append("\n")
				.append(state).append("\n")
				.append(resType);
		logger.info(sb.toString());
//		redirectUri += String.format(OAUTH_REDIRECT, ACCESS_TOKEN, state);
		ModelAndView mav = new ModelAndView("masterTemplate");
		mav.addObject("view", new View("login", "login"));
		mav.addObject("clientId", clientId);
		mav.addObject("redirectUri", redirectUri);
		mav.addObject("state", state);
		mav.addObject("responseType", resType);
		return mav;
	}
}
