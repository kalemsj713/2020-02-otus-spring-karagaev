package ru.kalemsj713.otus.exercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
		return Optional.ofNullable(em.find(Genre.class, id));

	}

	@Override
	public void deleteGenre(Genre genre) {
		em.remove(em.contains(genre) ? genre : em.merge(genre));
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
