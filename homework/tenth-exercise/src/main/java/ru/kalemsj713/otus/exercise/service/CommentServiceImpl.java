package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.dto.CommentDTO;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.CommentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<Comment> addNewComment(String text, long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return Optional.empty();
        }
        Comment comment = new Comment(text);
        comment.setBook(book.get());
        return Optional.of(commentRepository.save(comment));
    }

    @Override
    public CommentDTO addNewComment(CommentDTO commentDTO) {
        Optional<Comment> comment = addNewComment(commentDTO.getText(), commentDTO.getBookId());
        return new CommentDTO(comment.orElseThrow());
    }

}
