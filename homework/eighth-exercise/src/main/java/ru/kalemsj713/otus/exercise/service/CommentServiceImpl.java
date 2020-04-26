package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.CommentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public String addNewComment(String text, String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            Comment comment = commentRepository.save(new Comment(text));
            book.get().getComments().add(comment);
            bookRepository.save(book.get());
            return String.format("new Comment created:%s", comment);
        } else {
            return String.format("Book with id:%s not found", bookId);
        }
    }
    @Transactional
    @Override
    public String editComment(String id, String text) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return String.format("Comment with id:%s not found", id);
        } else {
            comment.get().setText(text);
            commentRepository.save(comment.get());
            return String.format("Comment saved:%s", comment.get());
        }
    }
    @Transactional
    @Override
    public String deleteComment(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            return String.format("Comment with id:%s deleted", id);
        } else {
            return String.format("Comment with id:%s not found", id);
        }
    }

    @Override
    public String getComment(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            return String.format("Comment with id:%s not found", id);
        } else {
            return String.format("Founded comment:%s", comment.get());
        }
    }
}
