package ru.kalemsj713.otus.exercise.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Transactional
@Repository
public class AuthorDaoImpl implements AuthorDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Author> getAuthor(Long id) {
		Optional<Author> author = Optional.ofNullable(em.find(Author.class, id));
		author.ifPresent(value -> Hibernate.initialize(value.getBooks()));
		return author;
	}

	@Override
	public void deleteAuthor(Long id) {
		Author author = em.find(Author.class, id);
		em.remove(author);
	}

	@Override
	public Author saveAuthor(Author author) {
		if (author.getId() <= 0) {
			em.persist(author);
			return author;
		} else {
			return em.merge(author);
		}
	}


}
