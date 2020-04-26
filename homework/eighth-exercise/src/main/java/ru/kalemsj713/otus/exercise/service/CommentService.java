package ru.kalemsj713.otus.exercise.service;

public interface CommentService {

    String addNewComment(String text, String bookId);

    String editComment(String id, String text);

    String deleteComment(String id);

    String getComment(String id);
}
