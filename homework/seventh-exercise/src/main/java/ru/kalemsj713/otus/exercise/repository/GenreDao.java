package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.Optional;

public interface GenreDao extends JpaRepository<Genre, Long> {
	@EntityGraph(attributePaths = "books")

	Optional<Genre> findGenreById(Long id);
}
