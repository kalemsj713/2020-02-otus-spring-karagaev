package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Question;

import java.util.List;

public interface QuestionDao {
	List<Question> prepareQuestionPack();
}
