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
import ru.kalemsj713.otus.exercise.controller.view.GenreController;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.dto.BookDTO;
import ru.kalemsj713.otus.exercise.service.GenreService;
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
@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GenreService genreService;
    @MockBean
    private BookService bookService;


    @BeforeEach
    void setUp() {
        Genre expected = new Genre("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        Genre genre2 = new Genre("firstName4");
        genre2.setBooks(Collections.singletonList(book));
        genre2.setId(1L);
        when(genreService.saveGenre(expected)).thenReturn(genre2);
        when(genreService.getGenreById(1L)).thenReturn(java.util.Optional.of(new Genre(1L, "1234")));

    }


    @Test
    void create() throws Exception {
        BookDTO book = new BookDTO(1L, "title1");
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));
        mvc.perform(get("/genre/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/new"))
                .andExpect(model().attribute("genre", new Genre()))
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
        Genre expected = new Genre("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        mvc.perform(post("/genre/new")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("genre", expected))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(genreService, times(1)).saveGenre(expected);
        verifyNoMoreInteractions(genreService);
    }

    @SneakyThrows

    @Test
    void edit() {
        BookDTO book = new BookDTO(1L, "title1");
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));
        mvc.perform(get("/genre/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/edit"))
                .andExpect(model().attribute("genre", new Genre(1L, "1234")))
                .andExpect(model().attribute("allBooks", hasSize(1)))
                .andExpect(model().attribute("allBooks", hasItem(allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("title1")))
                )));
        verify(genreService, times(1)).getGenreById(1L);
        verifyNoMoreInteractions(genreService);
        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);
    }

    @SneakyThrows
    @Test
    void saveGenre() {
        Genre expected = new Genre("firstName4");
        Book book = new Book(1L, "title1");
        expected.setBooks(Collections.singletonList(book));
        mvc.perform(post("/genre/edit?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("genre", expected))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/genre?id=1"));
        verify(genreService, times(1)).saveGenre(expected);
        verifyNoMoreInteractions(genreService);
    }

    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(get("/genre/delete?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(genreService, times(1)).deleteGenre(1L);
        verifyNoMoreInteractions(genreService);
    }

    @SneakyThrows
    @Test
    void show() {
        mvc.perform(get("/genre?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/show"))
                .andExpect(model().attribute("genre", new Genre(1L, "1234")));

        verify(genreService, times(1)).getGenreById(1L);
        verifyNoMoreInteractions(genreService);
    }
}