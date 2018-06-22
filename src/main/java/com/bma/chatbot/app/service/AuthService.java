package com.bma.chatbot.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bma.chatbot.app.vo.LoginVO;
import com.bma.chatbot.app.vo.User;

@Service
public class AuthService {
	
	private static Logger logger = Logger.getLogger(AuthService.class);
	
	private static List<User> users = new ArrayList<>();

	private static final String USERS_FILE_PATH = "C:\\tmp\\chatbotusers.csv";

	public User validateUserCredential(LoginVO loginCredentials) {
		for (User user: users) {
			if (user.getUsername().trim().equals(loginCredentials.getUsername())) {
				if (user.getPassword().trim().equals(loginCredentials.getUserpass())) {
					logger.info("User is valid!");
					return user;
				}
			}
		}
		return null;
	}

	static {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(USERS_FILE_PATH)))) {
			String line = "";
			while (null != (line = br.readLine())) {
				String[] parts = line.split(",");
				int index = 0;
				User user = new User();
				user.setId(parts[index++]);
				user.setUsername(parts[index++]);
				user.setPassword(parts[index++]);
				user.setFirstName(parts[index++]);
				user.setLastName(parts[index++]);
				user.setEmailAddress(parts[index]);
				users.add(user);
			}
		} catch (FileNotFoundException e) {
			logger.error("File Not Found. File Path: " + USERS_FILE_PATH, e);
		} catch (IOException e) {
			logger.error("IOException. File Path: " + USERS_FILE_PATH, e);
		}
	}
}
