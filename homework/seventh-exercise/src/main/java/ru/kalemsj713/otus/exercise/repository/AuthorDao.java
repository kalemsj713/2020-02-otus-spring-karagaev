package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.Author;

import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long> {
	@EntityGraph(attributePaths = "books")

	Optional<Author> findAuthorById(Long id);
}
