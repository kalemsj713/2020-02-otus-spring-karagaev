package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kalemsj713.otus.exercise.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
