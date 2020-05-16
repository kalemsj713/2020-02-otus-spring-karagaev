package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceTest {
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @BeforeEach
    void setUp() {
        given(bookRepository.save(new Book("123"))).willReturn(new Book(1L, "123"));
        given(bookRepository.save(new Book(2L, "123"))).willReturn(new Book(2L, "123"));
        given(bookRepository.findById(2L)).willReturn(Optional.of(new Book(2L, "123")));
        given(authorRepository.findAllByBooks(any())).willReturn(List.of(new Author(1L, "1")));
        given(genreRepository.findAllByBooks(any())).willReturn(List.of(new Genre(2L, "2")));
    }

    @Test
    void saveBook() {
        Book actualBook = bookService.saveBook(new Book("123"));
        assertThat(actualBook).isEqualToComparingFieldByField(new Book(1L, "123"));
        verify(bookRepository, times(1)).save(new Book("123"));
    }

    @Test
    void deleteBook() {
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void getBookFullInfoById() {
        Optional<Map<String, Object>> actualModel = bookService.getBookFullInfoById(2l);
        assertThat(actualModel.isPresent());
        assertEquals(3, actualModel.get().size());
    }

    @Test
    void findAll() {
        bookService.findAll();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById() {
        Optional<Book> bookGood = bookService.getBookById(2L);
        Optional<Book> bookBad = bookService.getBookById(1L);
        assertThat(bookBad.isEmpty());
        assertThat(bookGood.isPresent());
        assertThat(bookGood.get()).isEqualToComparingFieldByField(new Book(2L, "123"));
    }
}