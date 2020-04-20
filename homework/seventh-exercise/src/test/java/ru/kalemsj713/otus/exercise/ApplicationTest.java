package ru.kalemsj713.otus.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.kalemsj713.otus.exercise.repository.AuthorDao;
import ru.kalemsj713.otus.exercise.repository.BookDao;
import ru.kalemsj713.otus.exercise.repository.GenreDao;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
 		assertThat(applicationContext.getBean(AuthorDao.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookDao.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreDao.class)).isNotEqualTo(null);
	}
}