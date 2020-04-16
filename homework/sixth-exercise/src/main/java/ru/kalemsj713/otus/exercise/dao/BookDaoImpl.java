package ru.kalemsj713.otus.exercise.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository

public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Optional<Book> getBook(Long id) {
		Optional<Book> book = Optional.ofNullable(em.find(Book.class, id));
		book.ifPresent(value -> Hibernate.initialize(value.getComments()));
		return book;
	}

	@Transactional
	@Override
	public Book saveBook(Book book) {
		if (book.getId() <= 0) {
			em.persist(book);
			return book;
		} else {
			return em.merge(book);
		}
	}

	@Transactional
	@Override
	public void deleteBook(Long id) {
		Book book = em.find(Book.class, id);
		em.remove(book);
	}

	public List<Book> getAll() {
		TypedQuery<Book> query = em.createQuery("select b from Book b left join fetch b.comments", Book.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public int getBooksCount() {
		return getAll().size();
	}


}
