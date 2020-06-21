package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kalemsj713.otus.exercise.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
