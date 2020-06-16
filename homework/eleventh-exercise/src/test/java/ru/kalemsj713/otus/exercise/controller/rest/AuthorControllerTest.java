package ru.kalemsj713.otus.exercise.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorController authorController;
    private WebTestClient client = null;
    Author author = new Author("1L", "title1");
    Author author2 = new Author("1L", "1234");

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToController(authorController)
                .build();

        Flux<Author> result = Flux.create(authors -> {
            authors.next(author);
            authors.next(author2);
            authors.complete();
        });
        when(authorRepository.findAll()).thenReturn(result);
    }

    @Test
    public void testGetAll() throws Exception {
        client
                .get()
                .uri("/api/author/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Author.class).hasSize(2).contains(author, author2);
    }

}