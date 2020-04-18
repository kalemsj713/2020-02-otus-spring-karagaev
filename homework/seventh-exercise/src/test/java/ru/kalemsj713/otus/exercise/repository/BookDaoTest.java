package ru.kalemsj713.otus.exercise.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kalemsj713.otus.exercise.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

 @DataJpaTest
 @RunWith(SpringRunner.class)
class BookDaoTest {
	@Autowired
	BookDao bookDao;
	@Autowired
	private TestEntityManager em;

	@DirtiesContext
	@Test
	void getBook() {
		val optionalBook = bookDao.findBookById(1L);
		val expectedBook = em.find(Book.class, 1L);
		assertThat(optionalBook).isPresent().get()
				.isEqualToComparingFieldByField(expectedBook);
	}
	@DirtiesContext
	@Test
	void saveBook() {
		val optionalBook = new Book("name");
 		bookDao.save(optionalBook);
		assertThat(optionalBook.getId()).isGreaterThan(0);
		Book expectedBook = em.find(Book.class, optionalBook.getId());
		assertThat(optionalBook).isEqualToComparingFieldByField(expectedBook);
		optionalBook.setTitle("name1");
		bookDao.save(optionalBook);
		expectedBook = em.find(Book.class, optionalBook.getId());
		assertEquals(optionalBook.getTitle(), expectedBook.getTitle());
		assertEquals(optionalBook.getTitle(), "name1");
	}

	@DirtiesContext
	@Test
	void deleteBook() {
		val book = em.find(Book.class, 1L);
		assertThat(book).isNotNull();
		em.detach(book);
 		bookDao.deleteById(1L);
		val deletedBook = em.find(Book.class, 1L);
		assertThat(deletedBook).isNull();
	}
	@DirtiesContext
	@Test
	void getBooksCount() {
		long actualCount=bookDao.count();
		assertEquals(5,actualCount);
	}
	@DirtiesContext
	@Test
	void getAll() {
		List<Book> bookList= bookDao.findAll();
		assertEquals(bookList.size(),5);
		assertThat(bookList).allMatch(book -> book.getTitle()!=null&& !book.getTitle().isEmpty());
	}
}