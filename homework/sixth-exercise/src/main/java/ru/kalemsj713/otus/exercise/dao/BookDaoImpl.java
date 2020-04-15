package ru.kalemsj713.otus.exercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository

public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Book> getBook(Long id) {
		return Optional.ofNullable(em.find(Book.class, id));

	}

	@Override
	public Book saveBook(Book book) {
		if (book.getId() <= 0) {
			em.persist(book);
			return book;
		} else {
			return em.merge(book);
		}
	}

	@Override
	public void deleteBook(Book book) {
		em.remove(em.contains(book) ? book : em.merge(book));
	}

	public List<Book> getAll() {
		TypedQuery<Book> query = em.createQuery("select b from Book b left join fetch b.comments", Book.class);
		return query.getResultList();
	}

	@Override
	public int getBooksCount() {
		return em.createQuery("select b from Book b", Book.class)
				.getResultList().size();
	}


}
