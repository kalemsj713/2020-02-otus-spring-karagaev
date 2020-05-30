package ru.kalemsj713.otus.exercise.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kalemsj713.otus.exercise.dto.BookDTO;
import ru.kalemsj713.otus.exercise.service.BookService;

@RestController
@RequestMapping("api/book")

@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PatchMapping
    public void updateTitle(@RequestBody BookDTO bookDTO) {
        bookService.updateTitle(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") long id) {
        return bookService.getBookInfoById(id);
    }
}
