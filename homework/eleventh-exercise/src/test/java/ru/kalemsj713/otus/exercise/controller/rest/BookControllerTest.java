package ru.kalemsj713.otus.exercise.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.repository.BookRepository;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookController bookController;

    private WebTestClient client = null;
    Book book1 = new Book("1L", "title1");
    Book book2 = new Book("2L", "1234");

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToController(bookController)
                .build();

        Flux<Book> result = Flux.create(books -> {
            books.next(book1);
            books.next(book2);
            books.complete();
        });
        when(bookRepository.findAll()).thenReturn(result);
        when(bookRepository.save(book1)).thenReturn(Mono.just(book1));
        when(bookRepository.findById("1")).thenReturn(Mono.just(book1));
    }

    @Test
    public void testGetAll() throws Exception {
        client
                .get()
                .uri("/api/book/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class).hasSize(2).contains(book1, book2);
    }

    @Test
    void newBook() {
        client
                .post()
                .uri("/api/book/")
                .body(BodyInserters.fromObject(book1))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void updateTitle() {
        client
                .patch()
                .uri("/api/book/")
                .body(BodyInserters.fromObject(book1))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void delete() {
        client
                .delete()
                .uri("/api/book/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getBook() {
        client
                .get()
                .uri("/api/book/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class).hasSize(1).contains(book1);

    }
}