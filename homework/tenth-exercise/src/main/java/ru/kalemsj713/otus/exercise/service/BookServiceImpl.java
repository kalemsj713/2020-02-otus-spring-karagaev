package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.dto.AuthorDTO;
import ru.kalemsj713.otus.exercise.dto.BookDTO;
import ru.kalemsj713.otus.exercise.dto.GenreDTO;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
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
    public BookDTO getBookInfoById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        BookDTO bookDTO = new BookDTO(book.orElseThrow());
        List<Author> authors = authorRepository.findAllByBooks(book.get());
        List<Genre> genres = genreRepository.findAllByBooks(book.get());
        bookDTO.setAuthors(authors.stream().map(AuthorDTO::new).collect(Collectors.toList()));
        bookDTO.setGenres(genres.stream().map(GenreDTO::new).collect(Collectors.toList()));
        return bookDTO;
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateTitle(BookDTO bookDTO) {
        Book bookToUpdate = bookRepository.getOne(bookDTO.getId());
        bookToUpdate.setTitle(bookDTO.getTitle());
        bookRepository.save(bookToUpdate);
    }

}
