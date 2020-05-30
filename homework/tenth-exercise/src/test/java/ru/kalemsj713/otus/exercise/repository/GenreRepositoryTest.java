package ru.kalemsj713.otus.exercise.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    void findAllByBooks() {
        Book book = em.find(Book.class, 1L);
        List<Genre> actual = genreRepository.findAllByBooks(book);
        Genre genre = em.find(Genre.class, 1L);
        Genre genre2 = em.find(Genre.class, 2L);
        assertThat(actual).containsExactly(genre, genre2);
    }
}