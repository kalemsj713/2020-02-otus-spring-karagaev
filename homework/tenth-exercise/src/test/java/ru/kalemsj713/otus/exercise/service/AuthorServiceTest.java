package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceTest {
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        given(authorRepository.save(new Author("123"))).willReturn(new Author(1L, "123"));
        given(authorRepository.save(new Author(2L, "123"))).willReturn(new Author(2L, "123"));
        given(authorRepository.findById(2L)).willReturn(Optional.of(new Author(2L, "123")));
    }

    @Test
    void saveAuthor() {
        Author actualAuthor = authorService.saveAuthor(new Author("123"));
        assertThat(actualAuthor).isEqualToComparingFieldByField(new Author(1L, "123"));
        verify(authorRepository, times(1)).save(new Author("123"));
    }

    @Test
    void deleteAuthor() {
        authorService.deleteAuthor(1L);
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAll() {
        authorService.findAll();
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void getAuthorById() {
        Optional<Author> authorGood = authorService.getAuthorById(2L);
        Optional<Author> authorBad = authorService.getAuthorById(1L);
        assertThat(authorBad.isEmpty());
        assertThat(authorGood.isPresent());
        assertThat(authorGood.get()).isEqualToComparingFieldByField(new Author(2L, "123"));
    }
}