package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kalemsj713.otus.exercise.domain.Comment;

import java.util.List;

public interface CommentDao extends CrudRepository<Comment, Long> {
	List<Comment> findAllByBookId(Long book_id);

}
