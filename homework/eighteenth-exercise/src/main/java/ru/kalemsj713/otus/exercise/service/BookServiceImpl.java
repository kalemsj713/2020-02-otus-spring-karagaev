package ru.kalemsj713.otus.exercise.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    @HystrixCommand(fallbackMethod = "defaultSaveBook", commandKey = "book")
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultDeleteBook", commandKey = "book")
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    @HystrixCommand(fallbackMethod = "defaultGetBookFullInfoById", commandKey = "book")
    public Optional<Map<String, Object>> getBookFullInfoById(Long id) {
        Map<String, Object> model = new HashMap<>();
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.put("book", book.get());
            List<Author> authors = authorRepository.findAllByBooks(book.get());
            model.put("authors", authors);
            List<Genre> genres = genreRepository.findAllByBooks(book.get());
            model.put("genres", genres);
            return Optional.of(model);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultFindAll", commandKey = "book")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultGetBookById", commandKey = "book")
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public Book defaultSaveBook(Book book) {
        return new Book(-1L, "Dubrovskii", new ArrayList<>());
    }

    public void defaultDeleteBook(Long id) {
    }

    public Optional<Map<String, Object>> defaultGetBookFullInfoById(Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("book", new Book(-1L, "Dubrovskii", new ArrayList<>()));
        model.put("authors", Collections.singletonList(new Author(-1L, "Pushkin")));
        model.put("genres", Collections.singletonList(new Genre(-1L, "Horror")));
        return Optional.of(model);
    }

    public List<Book> defaultFindAll() {
        return Collections.singletonList(new Book(-1L, "Dubrovskii", new ArrayList<>()));
    }

    public Optional<Book> defaultGetBookById(long id) {
        return Optional.of(new Book(-1L, "Dubrovskii", new ArrayList<>()));
    }
}
