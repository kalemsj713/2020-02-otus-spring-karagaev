package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

public interface LibraryService {

	Book findBook();

	void addBook();

	void editBook(Book selectedBook);

	void deleteBook(Book selectedBook);

	Genre findGenre();

	Author findAuthor();

	void printBookInfo(Book selectedBook);

	void addBookGenry(Book selectedBook, Genre selectedGenre);

	void addBookAuthor(Book selectedBook, Author selectedAuthor);

	void findAllBook();


}
