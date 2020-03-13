package ru.kalemsj713.otus.exercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kalemsj713.otus.exercise.service.ExamService;

@Configuration
@ComponentScan
public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		ExamService examService = context.getBean(ExamService.class);
		examService.exam();
	}
}
