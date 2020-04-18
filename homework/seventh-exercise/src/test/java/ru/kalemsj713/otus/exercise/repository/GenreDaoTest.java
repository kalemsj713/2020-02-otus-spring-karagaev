package ru.kalemsj713.otus.exercise.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kalemsj713.otus.exercise.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
 class GenreDaoTest {
	@Autowired
	GenreDao genreDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getGenre() {
		val optionalGenre = genreDao.findById(1L);
		val expectedGenre = em.find(Genre.class, 1L);
		assertThat(optionalGenre).isPresent().get()
				.isEqualToComparingFieldByField(expectedGenre);
	}

	@Test
	void saveGenre() {
		val optionalGenre = new Genre("name");
 		genreDao.save(optionalGenre);
		assertThat(optionalGenre.getId()).isGreaterThan(0);
		Genre expectedGenre = em.find(Genre.class, optionalGenre.getId());
		assertThat(optionalGenre).isEqualToComparingFieldByField(expectedGenre);
		optionalGenre.setTitle("name1");
		genreDao.save(optionalGenre);
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
 		genreDao.deleteById(1L);
		val deletedGenre = em.find(Genre.class, 1L);
		assertThat(deletedGenre).isNull();
	}
}