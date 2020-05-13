package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByBooks(Book book);

}
