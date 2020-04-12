package ru.kalemsj713.otus.exercise.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kalemsj713.otus.exercise.domain.Author;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoTest {
	@Autowired
	private AuthorDao authorDao;

	@Test
	void getAuthor() {
		Author author = authorDao.getAuthor(1L);
		assertEquals("author1", author.getName());
	}

	@Test
	@DirtiesContext
	void deleteAuthor() {
		authorDao.deleteAuthor(new Author(1L, ""));
		Author author = authorDao.getAuthor(1L);
		assertNull(author);
	}

	@Test
	@DirtiesContext
	void createAuthor() {
		Author authorExpected = new Author(4L, "author5");
		authorDao.createAuthor(authorExpected);
		Author authorActual = authorDao.getAuthor(4L);
		assertEquals(authorExpected, authorActual);
	}
}