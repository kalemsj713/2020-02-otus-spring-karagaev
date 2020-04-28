package ru.kalemsj713.otus.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private BookService bookService;

    private Book bookExp = new Book("2", "123",
            Set.of(new Comment("1", "1")), Set.of(new Author("2", "2")), Set.of(new Genre("3", "3")));

    @BeforeEach
    void setUp() {
        given(genreRepository.findById("2")).willReturn(Optional.of(new Genre("2", "123")));
        given(authorRepository.findById("2")).willReturn(Optional.of(new Author("2", "123")));

        given(bookRepository.save(new Book("123"))).willReturn(new Book("1", "123"));
        given(bookRepository.save(bookExp)).willReturn(bookExp);
        given(bookRepository.findById("2")).willReturn(Optional.of(bookExp));
        given(bookRepository.count()).willReturn(3L);

        given(bookRepository.findBooksByTitle("2")).willReturn(List.of(new Book("1", "2")));
    }

    @Test
    void addNewBook() {
        String expected = bookService.addNewBook("123");
        verify(bookRepository, times(1)).save(new Book("123"));
        assertEquals("new Book created:Book(id=1, title=123, comments=[], authors=[], genres=[])", expected);
    }

    @Test
    void saveBook() {
        String badExpected = bookService.saveBook("1", "321");
        String expected = bookService.saveBook("2", "123");
        verify(bookRepository, times(2)).findById(any());
        verify(bookRepository, times(1)).save(bookExp);
        assertEquals("book saved: Book(id=2, title=123, comments=[Comment(id=1, text=1)]," +
                " authors=[Author(id=2, name=2)], genres=[Genre(id=3, title=3)])", expected);
        assertEquals("Book with id:1 not found", badExpected);
    }

    @Test
    void deleteBook() {
        String badExpected = bookService.deleteBook("1");
        String expected = bookService.deleteBook("2");
        verify(bookRepository, times(2)).findById(any());
        verify(bookRepository, times(1)).delete(bookExp);
        assertEquals(expected, "Book with id:2 deleted");
        assertEquals(badExpected, "Book with id:1 not found");
    }

    @Test
    void getBook() {
        String badExpected = bookService.getBook("1");
        String expected = bookService.getBook("2");
        verify(bookRepository, times(2)).findBooksByTitle(any());
        assertEquals(expected, "Founded books:[Book(id=1, title=2, comments=[], authors=[], genres=[])]");
        assertEquals(badExpected, "Book(s) with title:1 not found");
    }

    @Test
    void getBookCount() {
        String expected = bookService.getBookCount();
        verify(bookRepository, times(1)).count();
        assertEquals("All books count:3", expected);

    }

    @Test
    void getComments() {
        String badExpected = bookService.getComments("1");
        String expected = bookService.getComments("2");
        verify(bookRepository, times(2)).findById(any());
        assertEquals(expected, "Book with id:2 comments:[Comment(id=1, text=1)]");
        assertEquals(badExpected, "Book with id:1 not found");
    }

    @Test
    void addGenreBookRelations() {
        given(bookRepository.findById("2")).willReturn(Optional.of(new Book()));

        String badExpected = bookService.addGenreBookRelations("1", "1");
        String expected = bookService.addGenreBookRelations("2", "2");
        verify(bookRepository, times(2)).findById(any());
        verify(genreRepository, times(2)).findById(any());
        assertEquals(expected, "Book saved with new relation: Book(id=null, title=null, comments=[], " +
                "authors=[], genres=[Genre(id=2, title=123)])");
        assertEquals(badExpected, "Book with id:1 not found");
    }

    @Test
    void addAuthorBookRelations() {
        given(bookRepository.findById("2")).willReturn(Optional.of(new Book()));

        String badExpected = bookService.addAuthorBookRelations("1", "1");
        String expected = bookService.addAuthorBookRelations("2", "2");
        verify(bookRepository, times(2)).findById(any());
        verify(authorRepository, times(2)).findById(any());
        assertEquals(expected, "Book saved with new relation: Book(id=null, title=null," +
                " comments=[], authors=[], genres=[Genre(id=2, title=123)])");
        assertEquals(badExpected, "Book with id:1 not found");
    }

    @Test
    void deleteGenreBookRelations() {
        given(bookRepository.findById("2")).willReturn(Optional.of(new Book()));

        String badExpected = bookService.deleteGenreBookRelations("1", "1");
        String expected = bookService.deleteGenreBookRelations("2", "2");
        verify(bookRepository, times(2)).findById(any());
        verify(genreRepository, times(2)).findById(any());
        assertEquals(expected, "Book saved: Book(id=null, title=null, " +
                "comments=[], authors=[], genres=[])");
        assertEquals(badExpected, "Book with id:1 not found");
    }

    @Test
    void deleteAuthorBookRelations() {
        given(bookRepository.findById("2")).willReturn(Optional.of(new Book()));

        String badExpected = bookService.deleteAuthorBookRelations("1", "1");
        String expected = bookService.deleteAuthorBookRelations("2", "2");
        verify(bookRepository, times(2)).findById(any());
        verify(authorRepository, times(2)).findById(any());
        assertEquals(expected, "Book saved: Book(id=null, title=null, comments=[], authors=[], genres=[])");
        assertEquals(badExpected, "Book with id:1 not found");
    }
}