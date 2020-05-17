package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author saveAuthor(Author author);

    void deleteAuthor(Long id);

    List<AuthorDTO> findAll();

    Optional<Author> getAuthorById(long id);
}
