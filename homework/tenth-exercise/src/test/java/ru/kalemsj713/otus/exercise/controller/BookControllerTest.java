package ru.kalemsj713.otus.exercise.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.kalemsj713.otus.exercise.controller.view.BookViewController;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.service.BookService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BookViewController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;


    @BeforeEach
    void setUp() {
        Book expected = new Book("title1");
        Book book = new Book(1L, "title1");
        Book book2 = new Book(1L, "1234");
        when(bookService.saveBook(book2)).thenReturn(book2);
        when(bookService.saveBook(expected)).thenReturn(book);
        when(bookService.getBookById(1L)).thenReturn(java.util.Optional.of(new Book(1L, "title1")));
        Map<String, Object> model = new HashMap<>();
        model.put("book", book);
        when(bookService.getBookFullInfoById(1L)).thenReturn(java.util.Optional.of(model));
    }


    @Test
    void create() throws Exception {
        mvc.perform(get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/new"))
                .andExpect(model().attribute("book", new Book()));
    }

    @SneakyThrows
    @Test
    void postCreate() {
        Book book = new Book("title1");
        mvc.perform(post("/book/new")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/book?id=1"));
        verify(bookService, times(1)).saveBook(book);
        verifyNoMoreInteractions(bookService);
    }



    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(get("/book/delete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(bookService, times(1)).deleteBook(1L);
        verifyNoMoreInteractions(bookService);
    }


}