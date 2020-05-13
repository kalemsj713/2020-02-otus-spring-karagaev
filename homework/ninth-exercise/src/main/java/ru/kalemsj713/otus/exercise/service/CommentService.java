package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Comment;

import java.util.Optional;

public interface CommentService {

    void deleteComment(Long id);

    Optional<Comment> addNewComment(String text, long bookId);
}
