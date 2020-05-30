package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.dto.BookDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(Long id);

    List<BookDTO> findAll();

    Optional<Map<String, Object>> getBookFullInfoById(Long id);

    BookDTO getBookInfoById(Long id);

    Optional<Book> getBookById(long id);

    void updateTitle(BookDTO bookDTO);
}
