package ru.kalemsj713.otus.exercise;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.kalemsj713.otus.exercise.service.ExamService;

public class Exercise {

	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		ExamService service = context.getBean(ExamService.class);
		service.exam();

	}
}
