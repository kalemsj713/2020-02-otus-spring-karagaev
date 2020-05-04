package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {


    Book saveBook(Book book);

    void deleteBook(Long id);

    Optional<Map<String, Object>> getBookFullInfoById(Long id);

    List<Book> findAll();

    Optional<Book> getBookById(long id);
}
