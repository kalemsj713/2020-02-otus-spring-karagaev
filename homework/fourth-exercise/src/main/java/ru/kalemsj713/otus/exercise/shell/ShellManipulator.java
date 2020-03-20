package ru.kalemsj713.otus.exercise.shell;


import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellManipulator {

	private String userName;


	@ShellMethod(value = "Login command", key = {"l", "login"})
	public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
		this.userName = userName;
		return String.format("Добро пожаловать: %s", userName);
	}

	@ShellMethod(value = "Publish event command", key = {"p", "pub", "publish"})
	@ShellMethodAvailability(value = "isPublishEventCommandAvailable")
	public String publishEvent() {
		return "Событие опубликовано";

	}

	private Availability isPublishEventCommandAvailable() {
		return userName == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
	}
}
