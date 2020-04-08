package ru.kalemsj713.otus.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.kalemsj713.otus.exercise.dao.AuthorDao;
import ru.kalemsj713.otus.exercise.dao.BookDao;
import ru.kalemsj713.otus.exercise.dao.GenreDao;
import ru.kalemsj713.otus.exercise.service.LibraryService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertThat(applicationContext.getBean(LibraryService.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(AuthorDao.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(BookDao.class)).isNotEqualTo(null);
		assertThat(applicationContext.getBean(GenreDao.class)).isNotEqualTo(null);
	}
}