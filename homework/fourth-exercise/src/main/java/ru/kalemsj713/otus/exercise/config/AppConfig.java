package ru.kalemsj713.otus.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {
	@Bean
	public Locale locale(@Value("${spring.mvc.locale}") Locale locale) {
		return locale;
	}
}
