package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
	@Override
	public List<Question> getQuestionPack() {
		List<Question> mock= new ArrayList<>();
		List answers= new ArrayList();
		answers.add("first answer");
		answers.add("second answer");
		answers.add("third answer");
		mock.add(new Question("hello mock qs",1,answers));
		return mock;
	}
}
