package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;

public interface BookDao {

	int getBooksCount();

	Book getBookById(Long id);



	void deleteBook(Book book);

	void createBook(String title);

	void updateBook(Book book, String newTitle);
	List<Book> getAll();
	String getBookInfo(Book selectedBook);

	void addBookGenry(Book selectedBook, Genre selectedGenre);

	void addBookAuthor(Book selectedBook, Author selectedAuthor);
}
