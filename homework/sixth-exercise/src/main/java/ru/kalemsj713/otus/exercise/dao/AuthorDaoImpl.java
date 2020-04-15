package ru.kalemsj713.otus.exercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Transactional
@Repository
public class AuthorDaoImpl implements AuthorDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Author> getAuthor(Long id) {
		TypedQuery<Author> query = em.createQuery(
				"SELECT a FROM Author a LEFT JOIN FETCH  a.books b where  a.id=:id", Author.class);
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public void deleteAuthor(Author author) {
 		em.remove(em.contains(author) ? author : em.merge(author));
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
