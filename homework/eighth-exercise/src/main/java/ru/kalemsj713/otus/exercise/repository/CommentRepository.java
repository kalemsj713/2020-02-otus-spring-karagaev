package ru.kalemsj713.otus.exercise.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kalemsj713.otus.exercise.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
