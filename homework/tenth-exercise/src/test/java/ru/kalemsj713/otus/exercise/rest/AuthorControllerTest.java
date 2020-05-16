package ru.kalemsj713.otus.exercise.rest;

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
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;

import java.util.Collections;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;


    @BeforeEach
    void setUp() {
        Author expected = new Author("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        Author author2 = new Author("firstName4");
        author2.setBooks(Collections.singletonList(book));
        author2.setId(1L);
        when(authorService.saveAuthor(expected)).thenReturn(author2);
        when(authorService.getAuthorById(1L)).thenReturn(java.util.Optional.of(new Author(1L, "1234")));

    }


    @Test
    void create() throws Exception {
        Book book = new Book(1L, "title1");
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));
        mvc.perform(get("/author/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/new"))
                .andExpect(model().attribute("author", new Author()))
                .andExpect(model().attribute("allBooks", hasSize(1)))
                .andExpect(model().attribute("allBooks", hasItem(allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("title1")))
                )));
        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @SneakyThrows
    @Test
    void postCreate() {
        Author expected = new Author("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        mvc.perform(post("/author/new")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("author", expected))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(authorService, times(1)).saveAuthor(expected);
        verifyNoMoreInteractions(authorService);
    }

    @SneakyThrows

    @Test
    void edit() {
        Book book = new Book(1L, "title1");
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));
        mvc.perform(get("/author/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/edit"))
                .andExpect(model().attribute("author", new Author(1L, "1234")))
                .andExpect(model().attribute("allBooks", hasSize(1)))
                .andExpect(model().attribute("allBooks", hasItem(allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("title1")))
                )));
        verify(authorService, times(1)).getAuthorById(1L);
        verifyNoMoreInteractions(authorService);
        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @SneakyThrows
    @Test
    void saveAuthor() {
        Author expected = new Author("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        mvc.perform(post("/author/edit?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("author", expected))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/author?id=1"));
        verify(authorService, times(1)).saveAuthor(expected);
        verifyNoMoreInteractions(authorService);
    }

    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(get("/author/delete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(authorService, times(1)).deleteAuthor(1L);
        verifyNoMoreInteractions(authorService);
    }

    @SneakyThrows
    @Test
    void show() {

        mvc.perform(get("/author?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/show"))
                .andExpect(model().attribute("author", new Author(1L, "1234")));

        verify(authorService, times(1)).getAuthorById(1L);
        verifyNoMoreInteractions(authorService);

    }
}