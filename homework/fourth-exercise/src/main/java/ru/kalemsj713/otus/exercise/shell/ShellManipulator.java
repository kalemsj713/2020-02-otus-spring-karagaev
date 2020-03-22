package ru.kalemsj713.otus.exercise.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.kalemsj713.otus.exercise.service.ExamService;
import ru.kalemsj713.otus.exercise.service.MessageService;

@ShellComponent
public class ShellManipulator {

	private final ExamService examService;
	private final MessageService messageService;


	public ShellManipulator(ExamService examService, MessageService messageService) {
		this.examService = examService;
		this.messageService = messageService;
	}

	@ShellMethod(value = "Greeting command", key = {"g", "gr", "greeting"})
	public String greeting() {
		examService.greeting();
		return messageService.getMessage("exam.message.thanks");
	}

	@ShellMethod(value = "Start exam command", key = {"s", "st", "start"})
	@ShellMethodAvailability(value = "isExamAvailable")
	public void startExam() {
		examService.exam();
	}

	private Availability isExamAvailable() {
		return examService.isExamAvailable() ?
				Availability.available() : Availability.unavailable(messageService.getMessage("exam.message.introduce"));
	}
}
