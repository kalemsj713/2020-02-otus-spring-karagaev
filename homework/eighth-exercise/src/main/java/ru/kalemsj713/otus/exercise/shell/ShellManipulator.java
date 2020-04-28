package ru.kalemsj713.otus.exercise.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kalemsj713.otus.exercise.service.AuthorService;
import ru.kalemsj713.otus.exercise.service.BookService;
import ru.kalemsj713.otus.exercise.service.CommentService;
import ru.kalemsj713.otus.exercise.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ShellManipulator {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CommentService commentService;
    private final GenreService genreService;


    @ShellMethod(value = "add new author", key = {"aa"})
    public String addNewAuthor(@ShellOption() String name) {
        return authorService.addNewAuthor(name);
    }

    @ShellMethod(value = "save author ", key = {"sa"})
    public String saveAuthor(@ShellOption() String id, @ShellOption() String name) {
        return authorService.saveAuthor(id, name);
    }

    @ShellMethod(value = "delete author ", key = {"da"})
    public String deleteAuthor(@ShellOption() String id) {
        return authorService.deleteAuthor(id);
    }

    @ShellMethod(value = "get author", key = {"ga"})
    public String getAuthor(@ShellOption() String name) {
        return authorService.getAuthor(name);
    }


    @ShellMethod(value = "add new genre", key = {"ag"})
    public String addNewGenre(@ShellOption() String title) {
        return genreService.addNewGenre(title);
    }

    @ShellMethod(value = "save genre ", key = {"sg"})
    public String saveGenre(@ShellOption() String id, @ShellOption() String title) {
        return genreService.saveGenre(id, title);

    }

    @ShellMethod(value = "delete genre ", key = {"dg"})
    public String deleteGenre(@ShellOption() String id) {
        return genreService.deleteGenre(id);
    }

    @ShellMethod(value = "get genre", key = {"gg"})
    public String getGenre(@ShellOption() String title) {
        return genreService.getGenre(title);
    }

    @ShellMethod(value = "add new book", key = {"ab"})
    public String addNewBook(@ShellOption() String title) {
        return bookService.addNewBook(title);
    }

    @ShellMethod(value = "save book ", key = {"sb"})
    public String saveBook(@ShellOption() String id, @ShellOption() String title) {
        return bookService.saveBook(id, title);
    }

    @ShellMethod(value = "delete book ", key = {"db"})
    public String deleteBook(@ShellOption() String id) {
        return bookService.deleteBook(id);
    }

    @ShellMethod(value = "get book", key = {"gb"})
    public String getBook(@ShellOption() String title) {
        return bookService.getBook(title);
    }

    @ShellMethod(value = "get book count", key = {"gbc"})
    public String getBookCount() {
        return bookService.getBookCount();
    }


    @ShellMethod(value = "add new comment", key = {"ac"})
    public String addComment(@ShellOption() String text, @ShellOption() String bookId) {
        return commentService.addNewComment(text, bookId);
    }

    @ShellMethod(value = "edit comment ", key = {"sc"})
    public String saveComment(@ShellOption() String id, @ShellOption() String text) {

        return commentService.editComment(id, text);
    }

    @ShellMethod(value = "delete comment ", key = {"dc"})
    public String deleteComment(@ShellOption() String id) {
        return commentService.deleteComment(id);
    }

    @ShellMethod(value = "get comment", key = {"gc"})
    public String getComment(@ShellOption() String id) {
        return commentService.getComment(id);
    }

    @ShellMethod(value = "get comments book", key = {"gcb"})
    public String getComments(@ShellOption() String id) {
        return bookService.getComments(id);
    }

    @ShellMethod(value = "add genre book relations", key = {"agb"})
    public String addGenreToBook(@ShellOption() String bid, @ShellOption() String gid) {
        return bookService.addGenreBookRelations(bid, gid);
    }

    @ShellMethod(value = "add author book", key = {"aab"})
    public String addAuthorToBook(@ShellOption() String bid, @ShellOption() String aid) {
        return bookService.addAuthorBookRelations(bid, aid);

    }

    @ShellMethod(value = "delete genre book", key = {"dgb"})
    public String deleteGenreToBook(@ShellOption() String bid, @ShellOption() String gid) {
        return bookService.deleteGenreBookRelations(bid, gid);
    }

    @ShellMethod(value = "delete author book", key = {"dab"})
    public String deleteAuthorToBook(@ShellOption() String bid, @ShellOption() String aid) {
        return bookService.deleteAuthorBookRelations(bid, aid);

    }

}
