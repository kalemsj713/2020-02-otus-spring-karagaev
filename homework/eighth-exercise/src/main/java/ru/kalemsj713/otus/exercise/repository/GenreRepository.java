package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    List<Genre> findGenresByTitle(String title);
}
