package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CommentServiceImpl.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        given(commentRepository.save(new Comment("123"))).willReturn(new Comment("1", "123"));
        given(commentRepository.save(new Comment("2", "123"))).willReturn(new Comment("2", "123"));
        given(bookRepository.findById("2")).willReturn(Optional.of(new Book("2", "123")));

        given(commentRepository.findById("2")).willReturn(Optional.of(new Comment("2", "123")));

    }

    @Test
    void addNewComment() {
        String badActual = commentService.addNewComment("1", "321");

        String actual = commentService.addNewComment("123", "2");
        verify(commentRepository, times(1)).save(new Comment("123"));
        verify(bookRepository, times(2)).findById(any());

        assertEquals("new Comment created:Comment(id=1, text=123)", actual);
        assertEquals("Book with id:321 not found", badActual);

    }

    @Test
    void saveComment() {
        String badActual = commentService.editComment("1", "321");
        String actual = commentService.editComment("2", "321");
        verify(commentRepository, times(2)).findById(any());
        verify(commentRepository, times(1)).save(new Comment("2", "321"));
        assertEquals(actual, "Comment saved:Comment(id=2, text=321)");
        assertEquals(badActual, "Comment with id:1 not found");

    }

    @Test
    void deleteComment() {
        String badActual = commentService.deleteComment("1");
        String actual = commentService.deleteComment("2");
        verify(commentRepository, times(2)).findById(any());
        verify(commentRepository, times(1)).delete(new Comment("2", "123"));
        assertEquals(actual, "Comment with id:2 deleted");
        assertEquals(badActual, "Comment with id:1 not found");
    }

    @Test
    void getComment() {
        String badActual = commentService.getComment("1");
        String actual = commentService.getComment("2");
        verify(commentRepository, times(2)).findById(any());
        assertEquals(actual, "Founded comment:Comment(id=2, text=123)");
        assertEquals(badActual, "Comment with id:1 not found");
    }
}