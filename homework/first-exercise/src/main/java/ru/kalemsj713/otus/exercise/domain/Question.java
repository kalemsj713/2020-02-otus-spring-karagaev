package ru.kalemsj713.otus.exercise.domain;

import java.util.List;

public class Question {
	private final String questionText;
	private final int correctAnswerNumber;
	private final List<String> answers;

	public Question(String questionText, int correctAnswerNumber, List<String> answers) {
		this.questionText = questionText;
		this.correctAnswerNumber = correctAnswerNumber;
		this.answers = answers;
	}
}
