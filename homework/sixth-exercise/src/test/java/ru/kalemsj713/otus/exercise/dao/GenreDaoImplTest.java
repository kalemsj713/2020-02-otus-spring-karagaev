package ru.kalemsj713.otus.exercise.dao;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kalemsj713.otus.exercise.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {
	@Autowired
	GenreDao genreDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getGenre() {
		val optionalGenre = genreDao.getGenre(1L);
		val expectedGenre = em.find(Genre.class, 1L);
		assertThat(optionalGenre).isPresent().get()
				.isEqualToComparingFieldByField(expectedGenre);
	}

	@Test
	void saveGenre() {
		val optionalGenre = new Genre();
		optionalGenre.setTitle("name");
		genreDao.saveGenre(optionalGenre);
		assertThat(optionalGenre.getId()).isGreaterThan(0);
		Genre expectedGenre = em.find(Genre.class, optionalGenre.getId());
		assertThat(optionalGenre).isEqualToComparingFieldByField(expectedGenre);
		optionalGenre.setTitle("name1");
		genreDao.saveGenre(optionalGenre);
		expectedGenre = em.find(Genre.class, optionalGenre.getId());
		assertEquals(optionalGenre.getTitle(), expectedGenre.getTitle());
		assertEquals(optionalGenre.getTitle(), "name1");
	}

	@DirtiesContext
	@Test
	void deleteGenre() {
		val genre = em.find(Genre.class, 1L);
		assertThat(genre).isNotNull();
		em.detach(genre);
		Genre genre1 = genreDao.getGenre(1L).orElseThrow();
		genreDao.deleteGenre(genre1);
		val deletedGenre = em.find(Genre.class, 1L);
		assertThat(deletedGenre).isNull();
	}
}