package ru.kalemsj713.otus.exercise.service;

import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.dto.CommentDTO;

import java.util.Optional;

public interface CommentService {

    void deleteComment(Long id);

    Optional<Comment> addNewComment(String text, long bookId);

    CommentDTO addNewComment(CommentDTO commentDTO);
}
