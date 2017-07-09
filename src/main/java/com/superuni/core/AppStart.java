package com.superuni.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class AppStart {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[]{AppStart.class, AppConfig.class}, args);
	}

}
