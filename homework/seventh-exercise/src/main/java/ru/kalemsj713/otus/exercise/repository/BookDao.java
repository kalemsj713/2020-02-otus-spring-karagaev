package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {
	@EntityGraph(attributePaths = "comments")
	Optional<Book> findBookById(Long id);

}
