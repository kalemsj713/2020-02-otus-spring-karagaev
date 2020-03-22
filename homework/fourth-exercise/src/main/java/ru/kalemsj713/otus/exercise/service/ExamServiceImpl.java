package ru.kalemsj713.otus.exercise.service;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.dao.QuestionDao;
import ru.kalemsj713.otus.exercise.domain.Question;

import java.util.List;
import java.util.Scanner;

@Service
@Setter
@Getter
public class ExamServiceImpl implements ExamService {

	private final QuestionDao questionDao;
	private final MessageService messageService;

	private String examineeName;
	private String examineeFamily;


	private int result = 0;

	@Value("${exam.count}")
	private Integer sizePack;

	public ExamServiceImpl(QuestionDao questionDao, MessageService messageService) {
		this.questionDao = questionDao;
		this.messageService = messageService;
	}

	@Override
	public void exam() {
		quiz();
		showResults();
	}

	@Override
	public void greeting() {
		messageService.printMessage("exam.message.hello");
		messageService.printMessage("exam.message.name");
		Scanner scanner = new Scanner(System.in);
		examineeName = scanner.nextLine();
		messageService.printMessage("exam.message.surname");
		examineeFamily = scanner.nextLine();
	}

	@Override
	public boolean isExamAvailable() {
		return examineeName != null && examineeFamily != null;
	}

	private void quiz() {
		messageService.printMessage("exam.message.start", examineeName, examineeFamily);
		List<Question> questionPack = questionDao.getQuestionPack();
		if (sizePack > questionPack.size()) {
			sizePack = questionPack.size();
		}
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < sizePack; i++) {
			Question question = questionPack.get(i);
			messageService.printMessage("exam.message.question", i + 1, question.getQuestionText());
			for (int j = 0; j < question.getAnswers().size(); j++) {
				messageService.printMessage("exam.message.answer", j + 1, question.getAnswers().get(j));
			}
			messageService.printMessage("exam.message.enter");

			if (scanner.hasNextInt() && scanner.nextInt() == question.getCorrectAnswerNumber()) {
				result = result + 1;
				messageService.printMessage("exam.message.right");
			} else {
				messageService.printMessage("exam.message.wrong",
						question.getCorrectAnswerNumber() + 1, question.getAnswers().get(question.getCorrectAnswerNumber()));

			}
		}
	}

	private void showResults() {
		messageService.printMessage("exam.message.end", examineeName, examineeFamily);
		messageService.printMessage("exam.message.result", result, sizePack);
		result = 0;
	}
}
