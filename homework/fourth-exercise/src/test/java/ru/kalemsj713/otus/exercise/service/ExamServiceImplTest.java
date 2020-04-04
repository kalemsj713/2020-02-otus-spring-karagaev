package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kalemsj713.otus.exercise.dao.QuestionDao;
import ru.kalemsj713.otus.exercise.domain.Question;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ExamServiceImplTest {

	private ExamService examService;
	@Mock
	QuestionDao questionDao;
	MessageService messageService;

	@BeforeEach
	void setUp() {
		given(questionDao.getQuestionPack()).willReturn(new ArrayList<Question>());
		examService = new ExamServiceImpl(questionDao, messageService);
	}

	@Test
	void isExamAvailable() {
		assertFalse(examService.isExamAvailable());
	}
}