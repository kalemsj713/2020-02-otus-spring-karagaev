package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kalemsj713.otus.exercise.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
