package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Author;

import java.util.Optional;

public interface AuthorDao {


	Optional<Author> getAuthor(Long id);

	Author saveAuthor(Author author);

	void deleteAuthor(Long id);

}
