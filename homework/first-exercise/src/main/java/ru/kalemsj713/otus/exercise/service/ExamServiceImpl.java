package ru.kalemsj713.otus.exercise.service;


import ru.kalemsj713.otus.exercise.dao.QuestionDao;
import ru.kalemsj713.otus.exercise.domain.Question;

import java.util.List;
import java.util.Scanner;

public class ExamServiceImpl implements ExamService {
	private String examineeName;
	private String examineeFamily;
	private final QuestionDao questionDao;

	public ExamServiceImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public void exam() {
		greeting();
		quiz();
		showResults();
	}

	private void greeting() {
		System.out.println("Hello, examinee.");
		System.out.println("What is your name?");
		Scanner scanner = new Scanner(System.in);
		examineeName = scanner.nextLine();
		System.out.println("What is your surname?");
		examineeFamily = scanner.nextLine();
		System.out.println(String.format("%s %s, let's go exam!", examineeName, examineeFamily));
	}

	private int result = 0;

	private void quiz() {
		List<Question> questionPack = questionDao.getQuestionPack();
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < questionPack.size(); i++) {
			Question question = questionPack.get(i);
			System.out.println(String.format("Question #%d :%s", i + 1, question.getQuestionText()));
			for (int j = 0; j < question.getAnswers().size(); j++) {
				System.out.println(String.format("Answer #%d: %s", j + 1, question.getAnswers().get(j)));
			}
			if (scanner.hasNextInt() && scanner.nextInt() == question.getCorrectAnswerNumber()) {
				result = result + 1;
				System.out.println("Right.");
			} else {
				System.out.println(String.format("Wrong! Correct answer #%d: %s", question.getCorrectAnswerNumber() + 1, question.getAnswers().get(question.getCorrectAnswerNumber())));
			}
		}
	}

	private void showResults() {
		System.out.println(String.format("%s %s, test end.", examineeName, examineeFamily));
		System.out.println(String.format("Your results: %d. Well done!", result));

	}
}
