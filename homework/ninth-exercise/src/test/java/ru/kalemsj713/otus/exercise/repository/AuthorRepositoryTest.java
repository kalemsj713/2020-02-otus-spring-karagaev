package ru.kalemsj713.otus.exercise.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    void findAllByBooks() {
        Book book = em.find(Book.class, 1L);
        List<Author> actual = authorRepository.findAllByBooks(book);
        Author author = em.find(Author.class, 1L);
        Author author2 = em.find(Author.class, 2L);
        assertThat(actual).containsExactly(author, author2);
    }
}