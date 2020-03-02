package ru.kalemsj713.otus.exercise;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.kalemsj713.otus.exercise.service.ExamService;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		ExamService examService = context.getBean(ExamService.class);
		examService.exam();
	}
}
