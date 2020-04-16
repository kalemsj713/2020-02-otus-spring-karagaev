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
import ru.kalemsj713.otus.exercise.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {
	@Autowired
	AuthorDao authorDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getAuthor() {
		val optionalAuthor = authorDao.getAuthor(1L);
		val expectedAuthor = em.find(Author.class, 1L);
		assertThat(optionalAuthor).isPresent().get()
				.isEqualToComparingFieldByField(expectedAuthor);
	}

	@Test
	void saveAuthor() {
		val optionalAuthor = new Author();
		optionalAuthor.setName("name");
		authorDao.saveAuthor(optionalAuthor);
		assertThat(optionalAuthor.getId()).isGreaterThan(0);
		Author expectedAuthor = em.find(Author.class, optionalAuthor.getId());
		assertThat(optionalAuthor).isEqualToComparingFieldByField(expectedAuthor);
		optionalAuthor.setName("name1");
		authorDao.saveAuthor(optionalAuthor);
		expectedAuthor = em.find(Author.class, optionalAuthor.getId());
		assertEquals(optionalAuthor.getName(), expectedAuthor.getName());
		assertEquals(optionalAuthor.getName(), "name1");
	}

	@DirtiesContext
	@Test
	void deleteAuthor() {
		val author = em.find(Author.class, 1L);
		assertThat(author).isNotNull();
		em.detach(author);
 		authorDao.deleteAuthor(1L);
		val deletedAuthor = em.find(Author.class, 1L);
		assertThat(deletedAuthor).isNull();
	}
}