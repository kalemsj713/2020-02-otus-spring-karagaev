package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.sql.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
