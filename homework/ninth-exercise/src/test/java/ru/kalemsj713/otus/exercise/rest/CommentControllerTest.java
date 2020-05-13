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
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.CommentService;

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
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CommentService commentService;
    @MockBean
    private BookService bookService;


    @BeforeEach
    void setUp() {

        Comment expected = new Comment(1L,"text");
        Book book = new Book(1L, "title1");
        expected.setBook(book);
        when(bookService.getBookById(1L)).thenReturn(java.util.Optional.of(new Book(1L, "title1")));
        when(commentService.addNewComment("text",1L )).thenReturn(java.util.Optional.of(expected));

    }


    @Test
    void create() throws Exception {
        mvc.perform(get("/comment/new?bookId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comment/new"))
                .andExpect(model().attribute("comment", new Comment()));
        verify(bookService, times(1)).getBookById(1L);
        verifyNoMoreInteractions(bookService);
    }

    @SneakyThrows
    @Test
    void postCreate() {
        Comment expected = new Comment("text");
        Book book = new Book(1L, "title1");
        expected.setBook(book);
        mvc.perform(post("/comment/new?bookId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("comment", expected))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/book?id=1"));
        verify(commentService, times(1)).addNewComment("text",1L);
        verifyNoMoreInteractions(commentService);
    }


    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(get("/comment/delete?id=1&bookId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/book?id=1"));
        verify(commentService, times(1)).deleteComment(1L);
        verifyNoMoreInteractions(commentService);
    }

}