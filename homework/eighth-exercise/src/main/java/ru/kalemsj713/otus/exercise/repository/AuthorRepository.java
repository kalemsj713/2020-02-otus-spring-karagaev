package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kalemsj713.otus.exercise.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findAuthorsByName(String name);
}
