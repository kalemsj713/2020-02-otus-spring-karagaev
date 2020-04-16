package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

	int getBooksCount();

	Optional<Book> getBook(Long id);

	Book saveBook(Book book);

	void deleteBook(Long id);

	List<Book> getAll();


}
