package ru.kalemsj713.otus.exercise.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Transactional
@Repository
public class GenreDaoImpl implements GenreDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Genre> getGenre(Long id) {
		Optional<Genre> genre = Optional.ofNullable(em.find(Genre.class, id));
		genre.ifPresent(value -> Hibernate.initialize(value.getBooks()));
		return genre;
	}

	@Override
	public void deleteGenre(Long id) {
		Genre genre = em.find(Genre.class, id);
		em.remove(genre);
	}

	@Override
	public Genre saveGenre(Genre genre) {
		if (genre.getId() <= 0) {
			em.persist(genre);
			return genre;
		} else {
			return em.merge(genre);
		}
	}

}
