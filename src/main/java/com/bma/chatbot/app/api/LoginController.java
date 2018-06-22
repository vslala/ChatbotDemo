package com.bma.chatbot.app.api;

import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.bma.chatbot.app.service.AuthService;
import com.bma.chatbot.app.utils.ChatbotConstants;
import com.bma.chatbot.app.utils.ChatbotUtil;
import com.bma.chatbot.app.vo.LoginVO;
import com.bma.chatbot.app.vo.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/user")
	public RedirectView authUser(@Valid LoginVO loginCredentials, BindingResult binding) {
		if (binding.hasErrors()) {
			logger.warn("Invalid Credentials!");
			return new RedirectView();
		}
		logger.debug(ChatbotUtil.printObj(loginCredentials));
		String redirectUri = loginCredentials.getRedirectUri();
		if (StringUtils.isEmpty(redirectUri)) {
			redirectUri = "https://oauth-redirect.googleusercontent.com/r/be-my-aficionado-1491969540599";
		}
		User user = authService.validateUserCredential(loginCredentials);
		String token = null;
		if (ChatbotUtil.isSet(user)) {
			token = generateToken(loginCredentials, user);
		} else {
			RedirectView redirect = new RedirectView(redirectUri + "?return=false");
			redirect.setStatusCode(HttpStatus.BAD_REQUEST);
			redirect.setUrl(redirectUri);
			return redirect;
		}
		
		RedirectView redirect = new RedirectView();
		redirectUri += String.format("#access_token=%s&token_type=%s&state=%s", token, "Bearer", loginCredentials.getState());
		redirect.setUrl(redirectUri);
		logger.info("Redirect Uri: " + redirectUri);
		return redirect;
	}

	

	private String generateToken(@Valid LoginVO loginCredentials, Object data) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 2);
		
		String jwt = Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("Chatbot Demo")
				.setExpiration(cal.getTime())
				.setAudience(loginCredentials.getClientId())
				.setIssuer("GP")
				.claim("data", ChatbotUtil.convertObjectToJSON(data))
				.signWith(SignatureAlgorithm.HS256, ChatbotConstants.SECRET_KEY.getBytes())
				.compact();
				
		return jwt;
	}
}
