package ru.kalemsj713.otus.exercise.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({BookDaoImpl.class, GenreDaoImpl.class, AuthorDaoImpl.class})
class BookDaoImplTest {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private GenreDao genreDao;
	@Autowired
	private AuthorDao authorDao;

	@Test
	void getBooksCount() {
		assertEquals(2, bookDao.getBooksCount());
	}

	@Test
	void getBookById() {
		Book bookExpected = new Book(1L, "book1");
		assertEquals(bookExpected, bookDao.getBookById(1L));
	}

	@Test
	void getAll() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "book1"));
		books.add(new Book(2L, "book2"));
		assertEquals(books, bookDao.getAll());

	}

	@DirtiesContext
	@Test
	void deleteBook() {
		bookDao.deleteBook(new Book(2L, "book2"));
		assertNull(bookDao.getBookById(2L));
	}

	@Test
	void createBook() {
		Book book = new Book(3L, "book3");
		bookDao.createBook("book3");
		assertEquals(book, bookDao.getBookById(3L));
	}

	@Test
	void updateBook() {
		String newTitle = "2title";
		bookDao.updateBook(bookDao.getBookById(2L), newTitle);
		assertEquals(newTitle, bookDao.getBookById(2L).getTitle());

	}

	@Test
	void getBookInfo() {
		assertEquals("Автор(ы):author1,author2, Жанр(ы):genre1", bookDao.getBookInfo(bookDao.getBookById(1L)));
	}

	@DirtiesContext
	@Test
	void addBookGenry() {
		bookDao.addBookGenry(bookDao.getBookById(1L), genreDao.getGenre(2L));
		assertEquals("Автор(ы):author1,author2, Жанр(ы):genre1,genre2", bookDao.getBookInfo(bookDao.getBookById(1L)));

	}

	@DirtiesContext
	@Test
	void addBookAuthor() {
		bookDao.addBookAuthor(bookDao.getBookById(1L), authorDao.getAuthor(3L));
		assertEquals("Автор(ы):author1,author2,author3, Жанр(ы):genre1", bookDao.getBookInfo(bookDao.getBookById(1L)));
	}
}