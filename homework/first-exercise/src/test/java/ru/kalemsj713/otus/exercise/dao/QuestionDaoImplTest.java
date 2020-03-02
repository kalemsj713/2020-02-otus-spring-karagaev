package ru.kalemsj713.otus.exercise.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kalemsj713.otus.exercise.domain.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionDaoImplTest {
	private QuestionDaoImpl questionDao;

	@BeforeEach
	void setUp() {
		questionDao = new QuestionDaoImpl("default.csv");
 	}

	@Test
	void getQuestionPack() {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("default.csv")), StandardCharsets.UTF_8))) {
			List<Question> questionPack = questionDao.getQuestionPack();
			List<Question> questionsPack = new ArrayList<>();
			List<String> questionFromFile = new ArrayList<>();
			String line = "";
			while ((line = br.readLine()) != null) {
				questionFromFile.add(line);
			}
			for (int i = 1; i < questionFromFile.size(); i++) {
				String[] qs = questionFromFile.get(i).split(";");
				Question question = new Question(qs[0], Integer.parseInt(qs[1]), Arrays.asList(qs[2], qs[3], qs[4], qs[5]));
				questionsPack.add(question);
			}
			assertThat(questionPack.size() == questionsPack.size());

		} catch (Exception e) {
			new AssertionError(e.getMessage());
		}
	}
}