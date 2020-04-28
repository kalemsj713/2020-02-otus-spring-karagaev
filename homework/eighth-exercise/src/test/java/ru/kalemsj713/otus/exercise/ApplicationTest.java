package ru.kalemsj713.otus.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.CommentRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.CommentService;
import ru.kalemsj713.otus.exercise.service.GenreService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext.getBean(AuthorRepository.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(BookRepository.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(GenreRepository.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(CommentRepository.class)).isNotEqualTo(null);

        assertThat(applicationContext.getBean(AuthorService.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(CommentService.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(BookService.class)).isNotEqualTo(null);
        assertThat(applicationContext.getBean(GenreService.class)).isNotEqualTo(null);


    }
}