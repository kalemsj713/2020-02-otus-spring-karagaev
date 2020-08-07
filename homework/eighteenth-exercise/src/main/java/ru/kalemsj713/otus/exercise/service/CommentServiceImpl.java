package ru.kalemsj713.otus.exercise.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @HystrixCommand(fallbackMethod = "defaultDeleteComment", commandKey = "comment")
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    @HystrixCommand(fallbackMethod = "defaultAddNewComment", commandKey = "comment")
    public Optional<Comment> addNewComment(String text, long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return Optional.empty();
        }
        Comment comment = new Comment(text);
        comment.setBook(book.get());
        return Optional.of(commentRepository.save(comment));
    }

    public void defaultDeleteComment(Long id) {
    }

    public Optional<Comment> defaultAddNewComment(String text, long bookId) {
        Comment comment = new Comment(-1L, text);
        comment.setBook(new Book(-1L, "Dubrovskii"));
        return Optional.of(comment);
    }
}
