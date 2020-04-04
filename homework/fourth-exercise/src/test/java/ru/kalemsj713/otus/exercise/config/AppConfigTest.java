package ru.kalemsj713.otus.exercise.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import ru.kalemsj713.otus.exercise.dao.QuestionDao;
import ru.kalemsj713.otus.exercise.service.ExamService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ru.kalemsj713.otus.exercise.config.AppConfig.class)
class AppConfigTest {

	@Autowired
	Locale locale;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ExamService examService;
	@Autowired
	QuestionDao questionDao;
	@Test
	void testBeans() {
		assertThat(locale).isNotNull();
		assertThat(messageSource).isNotNull();
		assertThat(examService).isNotNull();
		assertThat(questionDao).isNotNull();
	}
}