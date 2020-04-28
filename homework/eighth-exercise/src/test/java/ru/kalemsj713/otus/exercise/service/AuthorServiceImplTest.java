package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        given(authorRepository.save(new Author("123"))).willReturn(new Author("1", "123"));
        given(authorRepository.save(new Author("2", "123"))).willReturn(new Author("2", "123"));
        given(authorRepository.findById("2")).willReturn(Optional.of(new Author("2", "123")));
        given(authorRepository.findAuthorsByName("2")).willReturn(List.of(new Author("1", "2")));
    }

    @Test
    void addNewAuthor() {
        String expected = authorService.addNewAuthor("123");
        verify(authorRepository, times(1)).save(new Author("123"));
        assertEquals(expected, "new Author created:Author(id=1, name=123)");
    }

    @Test
    void saveAuthor() {
        String badExpected = authorService.saveAuthor("1", "321");
        String expected = authorService.saveAuthor("2", "321");
        verify(authorRepository, times(2)).findById(any());
        verify(authorRepository, times(1)).save(new Author("2", "321"));
        assertEquals(expected, "Author saved: Author(id=2, name=321)");
        assertEquals(badExpected, "Author with id:1 not found");

    }

    @Test
    void deleteAuthor() {
        String badExpected = authorService.deleteAuthor("1");
        String expected = authorService.deleteAuthor("2");
        verify(authorRepository, times(2)).findById(any());
        verify(authorRepository, times(1)).delete(new Author("2", "123"));
        assertEquals(expected, "Author with id:2 deleted");
        assertEquals(badExpected, "Author with id:1 not found");
    }

    @Test
    void getAuthor() {
        String badExpected = authorService.getAuthor("1");
        String expected = authorService.getAuthor("2");
        verify(authorRepository, times(2)).findAuthorsByName(any());
        assertEquals(expected, "Founded authors:[Author(id=1, name=2)]");
        assertEquals(badExpected, "Author(s) with name:1 not found");
    }
}