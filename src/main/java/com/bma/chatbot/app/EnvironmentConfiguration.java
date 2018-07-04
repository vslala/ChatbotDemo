package com.bma.chatbot.app;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:intent_mapper.properties")
public class EnvironmentConfiguration implements EnvironmentAware {
	
	
	private static Environment env;
	
	public static String getProperty(String propName) {
		return env.getProperty(propName);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
}
