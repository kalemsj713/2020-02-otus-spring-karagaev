package ru.kalemsj713.otus.exercise.dao;

import ru.kalemsj713.otus.exercise.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
	Optional<Comment> getComment(Long id);

	Comment saveComment(Comment comment);

	List<Comment> getComments(Long book_id);

	void deleteComment(Long id);


}
