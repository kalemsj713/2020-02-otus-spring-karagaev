package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kalemsj713.otus.exercise.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookCustomRepository {
}
