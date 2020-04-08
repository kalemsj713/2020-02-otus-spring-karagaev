package ru.kalemsj713.otus.exercise.service;

import org.springframework.stereotype.Service;
import ru.kalemsj713.otus.exercise.dao.AuthorDao;
import ru.kalemsj713.otus.exercise.dao.BookDao;
import ru.kalemsj713.otus.exercise.dao.GenreDao;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.Scanner;

@Service
public class LibraryServiceImpl implements LibraryService {
	private final AuthorDao authorDao;
	private final BookDao bookDao;
	private final GenreDao genreDao;

	public LibraryServiceImpl(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao) {
		this.authorDao = authorDao;
		this.bookDao = bookDao;
		this.genreDao = genreDao;
	}

	@Override
	public Book findBook() {
		System.out.println("Введите id книги");
		try {
			String id = new Scanner(System.in).nextLine();
			long bookId = Long.parseLong(id);
			return bookDao.getBookById(bookId);
		} catch (NumberFormatException ex) {
			return null;
		}

	}

	@Override
	public void addBook() {
		System.out.println("Введите название книги");
		String title = new Scanner(System.in).nextLine();
		if (title == null || title.isEmpty()) {
			System.out.println("Нельзя создать книгу без названия");
		} else {
			bookDao.createBook(title);
			System.out.println("Книга успешно создана!");
		}
	}

	@Override
	public void editBook(Book selectedBook) {
		System.out.println("Введите новое название книги ");
		String title = new Scanner(System.in).nextLine();
		if (title == null || title.isEmpty()) {
			System.out.println("Нельзя оставлять книгу без названия");
		} else {
			bookDao.updateBook(selectedBook, title);
			System.out.println("Книга успешно переименована!");
		}
	}

	@Override
	public void deleteBook(Book selectedBook) {
		bookDao.deleteBook(selectedBook);
		System.out.println("Книга успешно удалена!");
	}

	@Override
	public Genre findGenre() {
		System.out.println("Введите id жанра");
		String id = new Scanner(System.in).nextLine();
		long genreId;
		try {
			genreId = Long.parseLong(id);
		} catch (NumberFormatException ex) {
			return null;
		}
		return genreDao.getGenre(genreId);
	}

	@Override
	public Author findAuthor() {
		System.out.println("Введите id автора");
		String id = new Scanner(System.in).nextLine();
		try {
			return authorDao.getAuthor(Long.parseLong(id));
		} catch (NumberFormatException ex) {
			return null;
		}
	}


	@Override
	public void printBookInfo(Book selectedBook) {
		System.out.println("Название:"+selectedBook.getTitle()+";"+bookDao.getBookInfo(selectedBook));
	}

	@Override
	public void addBookGenry(Book selectedBook, Genre selectedGenre) {
		bookDao.addBookGenry(selectedBook, selectedGenre);
	}

	@Override
	public void addBookAuthor(Book selectedBook, Author selectedAuthor) {
		bookDao.addBookAuthor(selectedBook, selectedAuthor);
	}

	@Override
	public void findAllBook() {
		System.out.println(bookDao.getAll());
	}
}
