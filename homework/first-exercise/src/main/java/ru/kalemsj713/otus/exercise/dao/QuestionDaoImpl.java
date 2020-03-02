package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
	private String fileName;

	public QuestionDaoImpl(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Question> getQuestionPack() {
		List<Question> questionsPack = new ArrayList<>();
		String csvSplitBy = ";";
		List<String> questionFromFile = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				questionFromFile.add(line);
			}
			for (int i = 1; i < questionFromFile.size(); i++) {
				String[] qs = questionFromFile.get(i).split(csvSplitBy);
				Question question = new Question(qs[0], Integer.parseInt(qs[1]), Arrays.asList(qs[2], qs[3], qs[4], qs[5]));
				questionsPack.add(question);
			}
		} catch (Exception ex) {

		}


		return questionsPack;
	}

}
