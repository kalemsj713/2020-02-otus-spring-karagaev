package ru.kalemsj713.otus.exercise.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ComponentScan("ru.kalemsj713.otus.exercise.event")
class BookCustomRepositoryImplTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        mongoTemplate.getDb().drop();
    }

    @Test
    void removeAuthorArrayElementsById() {
        Author author = mongoTemplate.save(new Author("a1"));
        Book book = mongoTemplate.save(new Book("title"));
        book.getAuthors().add(author);
        book = mongoTemplate.save(book);
        assertThat(book.getAuthors()).isNotNull().hasSize(1);
        mongoTemplate.remove(author);
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertThat(book2.get().getAuthors()).isNotNull().hasSize(0);

    }

    @Test
    void removeCommentArrayElementsById() {
        Book book = mongoTemplate.save(new Book("title"));
        Comment comment = mongoTemplate.save(new Comment("a1"));
        book.getComments().add(comment);
        book = mongoTemplate.save(book);
        assertThat(book.getComments()).isNotNull().hasSize(1);
        mongoTemplate.remove(comment);
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertThat(book2.get().getComments()).isNotNull().hasSize(0);
    }

    @Test
    void removeGenreArrayElementsById() {
        Book book = mongoTemplate.save(new Book("title"));
        Genre genre = mongoTemplate.save(new Genre("a1"));
        book.getGenres().add(genre);
        book = mongoTemplate.save(book);
        assertThat(book.getGenres()).isNotNull().hasSize(1);
        mongoTemplate.remove(genre);
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertThat(book2.get().getGenres()).isNotNull().hasSize(0);
    }

    @Test
    void setAuthorArrayElementsById() {
        Book book = mongoTemplate.save(new Book("title"));
        Author author = mongoTemplate.save(new Author("a1"));
        book.getAuthors().add(author);
        book = mongoTemplate.save(book);
        assertThat(book.getAuthors()).isNotNull().hasSize(1);
        author.setName("123");
        mongoTemplate.save(author);
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertThat(book2.get().getAuthors()).isNotNull().hasSize(1);
        assertThat(book2.get().getAuthors().iterator().next().getName().equalsIgnoreCase("123"));
    }

    @Test
    void setGenreArrayElementsById() {
        Book book = mongoTemplate.save(new Book("title"));
        Genre genre = mongoTemplate.save(new Genre("a1"));
        book.getGenres().add(genre);
        book = mongoTemplate.save(book);
        assertThat(book.getGenres()).isNotNull().hasSize(1);
        genre.setTitle("123");
        mongoTemplate.save(genre);
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertThat(book2.get().getGenres()).isNotNull().hasSize(1);
        assertThat(book2.get().getGenres().iterator().next().getTitle().equalsIgnoreCase("123"));

    }
}