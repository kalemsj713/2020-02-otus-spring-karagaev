package ru.kalemsj713.otus.exercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {
	@PersistenceContext
	private EntityManager em;

	@Transactional

	@Override
	public Optional<Comment> getComment(Long id) {
		Comment comment = em.find(Comment.class, id);
		return Optional.ofNullable(comment);
	}

	@Override
	public List<Comment> getComments(Long bookId) {
		Book book = em.find(Book.class, bookId);
		TypedQuery<Comment> query = em.createQuery("select c " +
						"from Comment c " +
						"where c.book = :book",
				Comment.class);
		query.setParameter("book", book);
		return query.getResultList();
	}

	@Transactional

	@Override
	public void deleteComment(Long id) {
		Comment comment = em.find(Comment.class, id);
		em.remove(comment);
	}

	@Transactional

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
