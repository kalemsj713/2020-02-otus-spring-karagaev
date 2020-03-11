package ru.kalemsj713.otus.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {
	private final MessageSource messageSource;
	private final Locale locale;

	@Autowired
	public MessageService(MessageSource messageSource, Locale locale) {
		this.messageSource = messageSource;
		this.locale = locale;
	}

	public void printMessage(String messageName, Object... args) {
		System.out.println(getMessage(messageName, args));
	}

	public String getMessage(String messageName, Object... args) {
		return messageSource.getMessage(
				messageName,
				args,
				locale
		);
	}
}
