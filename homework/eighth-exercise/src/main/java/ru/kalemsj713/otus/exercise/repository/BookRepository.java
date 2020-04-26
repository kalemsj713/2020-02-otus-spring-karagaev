package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> ,BookCustomRepository {
    List<Book> findBooksByTitle(String title);
}
