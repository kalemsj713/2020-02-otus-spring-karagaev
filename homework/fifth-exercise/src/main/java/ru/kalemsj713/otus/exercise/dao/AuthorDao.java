package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Author;

public interface AuthorDao {


	Author getAuthor(Long id);

	void deleteAuthor(Author author);

	void createAuthor(Author author);

 }
