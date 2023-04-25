package com.per.application.sys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConstant {
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	public static String DB_URL;

	@Value("${spring.datasource.username}")
	private void setUsername(String key) {
		DB_USERNAME = key;
	}

	@Value("${spring.datasource.password}")
	private void setPassword(String key) {
		DB_PASSWORD = key;
	}

	@Value("${spring.datasource.url}")
	private void setUrl(String key) {
		DB_URL = key;
	}
}
