package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kalemsj713.otus.exercise.domain.sql.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
