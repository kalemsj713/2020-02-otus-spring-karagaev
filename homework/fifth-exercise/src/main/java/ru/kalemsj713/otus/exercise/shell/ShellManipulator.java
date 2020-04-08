package ru.kalemsj713.otus.exercise.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.service.LibraryService;

@ShellComponent
public class ShellManipulator {

	private final LibraryService libraryService;

	private Book selectedBook;
	private Author selectedAuthor;
	private Genre selectedGenre;


	public ShellManipulator(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@ShellMethod(value = "Find all book", key = {"fab", "find all book"})
	public void findAllBook() {
		libraryService.findAllBook();

	}

	@ShellMethod(value = "Find book", key = {"fb", "find book"})
	public void findBook() {
		selectedBook = libraryService.findBook();
		if (selectedBook == null) {
			System.out.println("Не удалось найти книгу по заданным параметрам поиска. ");
		} else {
			libraryService.printBookInfo(selectedBook);
		}
	}

	@ShellMethod(value = "Find author", key = {"fa", "find author"})
	public void findAuthor() {
		selectedAuthor = libraryService.findAuthor();
		if (selectedAuthor == null) {
			System.out.println("Не удалось найти автора по заданным параметрам поиска. ");
		} else {
			System.out.println("Автор успешно найден:" + selectedAuthor.getName());
		}
	}

	@ShellMethod(value = "Find genre", key = {"fg", "find genre"})
	public void findGenre() {
		selectedGenre = libraryService.findGenre();
		if (selectedGenre == null) {
			System.out.println("Не удалось найти жано по заданным параметрам поиска. ");
		} else {
			System.out.println("Жанр успешно найден");
		}
	}

	@ShellMethod(value = "Add new book to library", key = {"ab", "add book"})
	public void addBook() {
		libraryService.addBook();
	}

	@ShellMethod(value = "Edit title book", key = {"eb", "edit title book"})
	@ShellMethodAvailability(value = "isBookCommandAvailable")
	public void editBook() {
		libraryService.editBook(selectedBook);
		System.out.println("Название книги успешно изменено");
		selectedBook = null;

	}

	@ShellMethod(value = "Delete selected book", key = {"db", "delete book"})
	@ShellMethodAvailability(value = "isBookCommandAvailable")
	public void deleteBook() {
		libraryService.deleteBook(selectedBook);
		selectedBook = null;
	}

	@ShellMethod(value = "Add new book to author", key = {"aab", "add author to book"})
	@ShellMethodAvailability(value = "isBookCommandAvailable,isAuthorCommandAvailable")

	public void addAuthor() {
		libraryService.addBookAuthor(selectedBook, selectedAuthor);
	}

	@ShellMethod(value = "Add genry to book to library", key = {"abg", "add book genry"})
	@ShellMethodAvailability(value = "isBookCommandAvailable,isGenreCommandAvailable")

	public void addBookGenre() {
		libraryService.addBookGenry(selectedBook, selectedGenre);
	}


	private Availability isBookCommandAvailable() {
		return selectedBook == null ? Availability.unavailable("Сначала выберите книгу") : Availability.available();
	}

	private Availability isAuthorCommandAvailable() {
		return selectedAuthor == null ? Availability.unavailable("Сначала выберите автора") : Availability.available();
	}

	private Availability isGenreCommandAvailable() {
		return selectedGenre == null ? Availability.unavailable("Сначала выберите жанр") : Availability.available();
	}
}
