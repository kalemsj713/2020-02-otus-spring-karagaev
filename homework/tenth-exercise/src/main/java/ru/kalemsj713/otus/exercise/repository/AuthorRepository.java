package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByBooks(Book book);
}
