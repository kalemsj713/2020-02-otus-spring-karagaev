package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.kalemsj713.otus.exercise.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

}
