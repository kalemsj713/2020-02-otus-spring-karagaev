package ru.kalemsj713.otus.exercise.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Genre;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {
	@Autowired
	private GenreDao genreDao;

	@Test
	void getGenre() {
		Genre genre = genreDao.getGenre(1L);
		assertEquals("genre1", genre.getTitle());
	}

	@Test
	@DirtiesContext
	void deleteGenre() {
		genreDao.deleteGenre(new Genre(1L, ""));
		Genre genre = genreDao.getGenre(1L);
		assertNull(genre);
	}

	@Test
	@DirtiesContext
	void createGenre() {
		Genre genreExpected = new Genre(3L, "title5");
		genreDao.createGenre(genreExpected);
		Genre genreActual = genreDao.getGenre(3L);
		assertEquals(genreExpected, genreActual);
	}
}