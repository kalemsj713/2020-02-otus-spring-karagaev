package ru.kalemsj713.otus.exercise.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.repository.BookRepository;

@RestController
@RequestMapping("api/book")

@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @PostMapping()
    public Mono<Book> newBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PatchMapping
    public Mono<Book> updateTitle(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }

    @GetMapping("/all")
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id);
    }
}
