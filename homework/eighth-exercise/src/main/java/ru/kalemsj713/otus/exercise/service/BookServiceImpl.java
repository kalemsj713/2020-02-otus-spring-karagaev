package ru.kalemsj713.otus.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.AuthorRepository;
import ru.kalemsj713.otus.exercise.repository.BookRepository;
import ru.kalemsj713.otus.exercise.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public String addNewBook(String title) {
        Book book = bookRepository.save(new Book(title));
        return String.format("new Book created:%s", book);
    }

    @Transactional
    @Override
    public String saveBook(String id, String title) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setTitle(title);
            bookRepository.save(book.get());
            return String.format("book saved: %s", book.get());
        } else {
            return String.format("Book with id:%s not found", id);
        }
    }

    @Transactional
    @Override
    public String deleteBook(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return String.format("Book with id:%s deleted", id);
        } else {
            return String.format("Book with id:%s not found", id);
        }

    }

    @Override
    public String getBook(String title) {
        List<Book> books = bookRepository.findBooksByTitle(title);
        if (books.isEmpty()) {
            return String.format("Book(s) with title:%s not found", title);
        } else {
            return String.format("Founded books:%s", books);
        }
    }

    @Override
    public String getBookCount() {
        long booksCount = bookRepository.count();
        return String.format("All books count:%d", booksCount);
    }

    @Override
    public String getComments(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return String.format("Book with id:%s comments:%s", id, book.get().getComments());
        } else {
            return String.format("Book with id:%s not found", id);
        }
    }

    @Override
    @Transactional
    public String addGenreBookRelations(String bid, String gid) {
        Optional<Book> book = bookRepository.findById(bid);
        Optional<Genre> genre = genreRepository.findById(gid);
        if (book.isPresent() && genre.isPresent()) {
            book.get().getGenres().add(genre.get());
            bookRepository.save(book.get());
            return String.format("Book saved with new relation: %s", book.get());
        } else {
            if (book.isEmpty()) {
                return String.format("Book with id:%s not found", bid);
            } else {
                return String.format("Genre with id:%s not found", gid);
            }
        }
    }

    @Override
    @Transactional
    public String addAuthorBookRelations(String bid, String aid) {
        Optional<Book> book = bookRepository.findById(bid);
        Optional<Author> author = authorRepository.findById(aid);
        if (book.isPresent() && author.isPresent()) {
            book.get().getAuthors().add(author.get());
            bookRepository.save(book.get());
            return String.format("Book saved with new relation: %s", book.get());
        } else {
            if (book.isEmpty()) {
                return String.format("Book with id:%s not found", bid);
            } else {
                return String.format("Author with id:%s not found", aid);
            }
        }
    }

    @Override
    @Transactional
    public String deleteGenreBookRelations(String bid, String gid) {
        Optional<Book> book = bookRepository.findById(bid);
        Optional<Genre> genre = genreRepository.findById(gid);
        if (book.isPresent() && genre.isPresent()) {
            book.get().getGenres().remove(genre.get());
            bookRepository.save(book.get());
            return String.format("Book saved: %s", book.get());
        } else {
            if (book.isEmpty()) {
                return String.format("Book with id:%s not found", bid);
            } else {
                return String.format("Genre with id:%s not found", gid);
            }
        }
    }

    @Override
    @Transactional
    public String deleteAuthorBookRelations(String bid, String aid) {
        Optional<Book> book = bookRepository.findById(bid);
        Optional<Author> author = authorRepository.findById(aid);
        if (book.isPresent() && author.isPresent()) {
            book.get().getAuthors().remove(author.get());
            bookRepository.save(book.get());
            return String.format("Book saved: %s", book.get());
        } else {
            if (book.isEmpty()) {
                return String.format("Book with id:%s not found", bid);
            } else {
                return String.format("Author with id:%s not found", aid);
            }
        }
    }
}
