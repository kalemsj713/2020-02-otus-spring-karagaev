package ru.kalemsj713.otus.exercise.service;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.CommentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CommentServiceImpl.class)
class CommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        given(bookRepository.findById(2L)).willReturn(Optional.of(new Book(2L, "123")));
        Comment comment = new Comment("123", new Book(2L, "123"));
        given(commentRepository.save(comment))
                .willReturn((new Comment(1L, "123")));

    }

    @Test
    void deleteComment() {
        commentService.deleteComment(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }

    @Test
    void addNewComment() {
        val actual = commentService.addNewComment("123", 2L);
        val expected = Optional.of(new Comment(1L, "123"));
        assertEquals(expected, actual);
    }
}