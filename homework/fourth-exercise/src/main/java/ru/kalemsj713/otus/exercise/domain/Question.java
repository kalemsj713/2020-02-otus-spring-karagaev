package ru.kalemsj713.otus.exercise.domain;

import java.util.List;

public class Question {
	private final String questionText;
	private final int correctAnswerNumber;
	private final List<String> answers;

	public String getQuestionText() {
		return questionText;
	}

	public int getCorrectAnswerNumber() {
		return correctAnswerNumber;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public Question(String questionText, int correctAnswerNumber, List<String> answers) {
		this.questionText = questionText;
		this.correctAnswerNumber = correctAnswerNumber;
		this.answers = answers;
	}
}
