package ru.kalemsj713.otus.exercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Transactional
@Repository
public class CommentDaoImpl implements CommentDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Comment> getComment(Long id) {
		Comment comment = em.find(Comment.class, id);
		return Optional.ofNullable(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Comment comment = em.find(Comment.class, id);
		em.remove(comment);
	}


	@Override
	public Comment saveComment(Comment comment) {
		if (comment.getId() <= 0) {
			em.persist(comment);
			return comment;
		} else {
			return em.merge(comment);
		}
	}
}
