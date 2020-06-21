package ru.kalemsj713.otus.exercise.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {
    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreController genreController;

    private WebTestClient client = null;
    Genre genre = new Genre("1L", "title1");
    Genre genre2 = new Genre("1L", "1234");

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToController(genreController)
                .build();

        Flux<Genre> result = Flux.create(genres -> {
            genres.next(genre);
            genres.next(genre2);
            genres.complete();
        });
        when(genreRepository.findAll()).thenReturn(result);
    }

    @Test
    public void testGetAll() {
        client
                .get()
                .uri("/api/genre/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Genre.class).hasSize(2).contains(genre, genre2);
    }
}