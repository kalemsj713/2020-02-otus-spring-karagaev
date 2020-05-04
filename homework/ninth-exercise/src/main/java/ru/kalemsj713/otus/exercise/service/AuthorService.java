package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author saveAuthor(Author author);

    void deleteAuthor(Long id);


    List<Author> findAll();

    Optional<Author> getAuthorById(long id);
}
