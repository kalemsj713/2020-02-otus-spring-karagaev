package ru.kalemsj713.otus.exercise.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kalemsj713.otus.exercise.dao.AuthorDaoImpl;
import ru.kalemsj713.otus.exercise.dao.BookDaoImpl;
import ru.kalemsj713.otus.exercise.dao.CommentDaoImpl;
import ru.kalemsj713.otus.exercise.dao.GenreDaoImpl;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.domain.Genre;

@ShellComponent
@AllArgsConstructor
public class ShellManipulator {

	private final AuthorDaoImpl authorDao;
	private final BookDaoImpl bookDao;
	private final CommentDaoImpl commentDao;
	private final GenreDaoImpl genreDao;


	@ShellMethod(value = "add new author", key = {"aa"})
	public void addAuthor(@ShellOption() String name) {
		Author author = new Author();
		author.setName(name);
		author.setId(0L);
		authorDao.saveAuthor(author);
	}

	@ShellMethod(value = "save author ", key = {"sa"})
	public void saveAuthor(@ShellOption() long id, @ShellOption() String name) {
		Author author = authorDao.getAuthor(id).orElseThrow();
		author.setName(name);
		authorDao.saveAuthor(author);
	}

	@ShellMethod(value = "delete author ", key = {"da"})
	public void deleteAuthor(@ShellOption() long id) {
		Author author = authorDao.getAuthor(id).orElseThrow();
		authorDao.deleteAuthor(author);
	}

	@ShellMethod(value = "get author", key = {"ga"})
	public void getAuthor(@ShellOption() long id) {
		System.out.println(authorDao.getAuthor(id).orElseThrow());
	}


	@ShellMethod(value = "add new genre", key = {"ag"})
	public void addGenre(@ShellOption() String title) {
		Genre genre = new Genre();
		genre.setId(0L);

		genre.setTitle(title);
		genreDao.saveGenre(genre);
	}

	@ShellMethod(value = "save genre ", key = {"sg"})
	public void saveGenre(@ShellOption() long id, @ShellOption() String title) {
		Genre genre = genreDao.getGenre(id).orElseThrow();
		genre.setTitle(title);
		genreDao.saveGenre(genre);
	}

	@ShellMethod(value = "delete genre ", key = {"dg"})
	public void deleteGenre(@ShellOption() long id) {
		Genre genre = genreDao.getGenre(id).orElseThrow();
		genreDao.deleteGenre(genre);
	}

	@ShellMethod(value = "get genre", key = {"gg"})
	public void getGenre(@ShellOption() long id) {
		System.out.println(genreDao.getGenre(id).orElseThrow());
	}

	@ShellMethod(value = "add new book", key = {"ab"})
	public void addBook(@ShellOption() String title) {
		Book book = new Book();
		book.setId(0L);

		book.setTitle(title);
		bookDao.saveBook(book);
	}

	@ShellMethod(value = "save book ", key = {"sb"})
	public void saveBook(@ShellOption() long id, @ShellOption() String title) {
		Book book = bookDao.getBook(id).orElseThrow();
		book.setTitle(title);
		bookDao.saveBook(book);
	}

	@ShellMethod(value = "delete book ", key = {"db"})
	public void deleteBook(@ShellOption() long id) {
		Book book = bookDao.getBook(id).orElseThrow();
		bookDao.deleteBook(book);
	}

	@ShellMethod(value = "get book", key = {"gb"})
	public void getBook(@ShellOption() long id) {
		System.out.println(bookDao.getBook(id).orElseThrow().toStringInformative());
	}

	@ShellMethod(value = "get book count", key = {"gbc"})
	public void getBookCount() {
		System.out.println(bookDao.getBooksCount());
	}

	@ShellMethod(value = "get allBook", key = {"gba"})
	public void getAllBooks() {
		System.out.println(bookDao.getAll());
	}


	@ShellMethod(value = "add new comment", key = {"ac"})
	public void addComment(@ShellOption() String text, @ShellOption() long bookId) {
		Comment comment = new Comment();
		comment.setBook(bookDao.getBook(bookId).orElseThrow());
		comment.setText(text);
		comment.setId(0L);
		commentDao.saveComment(comment);
	}

	@ShellMethod(value = "save comment ", key = {"sc"})
	public void saveComment(@ShellOption() long id, @ShellOption() String text) {
		Comment comment = commentDao.getComment(id).orElseThrow();
		comment.setText(text);
		commentDao.saveComment(comment);
	}

	@ShellMethod(value = "delete comment ", key = {"dc"})
	public void deleteComment(@ShellOption() long id) {
 		commentDao.deleteComment(id);
	}

	@ShellMethod(value = "get comment", key = {"gc"})
	public void getComment(@ShellOption() long id) {
		System.out.println(commentDao.getComment(id));
	}


	@ShellMethod(value = "add genre book", key = {"agb"})
	public void addGenreToBook(@ShellOption() long bid, @ShellOption() long gid) {
		Book book = bookDao.getBook(bid).orElseThrow();
		Genre genre = genreDao.getGenre(gid).orElseThrow();
		genre.getBooks().add(book);
		genreDao.saveGenre(genre);
	}

	@ShellMethod(value = "add author book", key = {"aab"})
	public void addAuthorToBook(@ShellOption() long bid, @ShellOption() long aid) {
		Book book = bookDao.getBook(bid).orElseThrow();
		Author author = authorDao.getAuthor(aid).orElseThrow();
		author.getBooks().add(book);
		authorDao.saveAuthor(author);
	}

	@ShellMethod(value = "delete genre book", key = {"dgb"})
	public void deleteGenreToBook(@ShellOption() long bid, @ShellOption() long gid) {
		Book book = bookDao.getBook(bid).orElseThrow();
		Genre genre = genreDao.getGenre(gid).orElseThrow();
		genre.getBooks().remove(book);
		genre.getBooks().removeIf(b -> b.getId() == bid);
		genreDao.saveGenre(genre);
	}

	@ShellMethod(value = "delete author book", key = {"dab"})
	public void deleteAuthorToBook(@ShellOption() long bid, @ShellOption() long aid) {
		Book book = bookDao.getBook(bid).orElseThrow();
		Author author = authorDao.getAuthor(aid).orElseThrow();
		author.getBooks().removeIf(b -> b.getId() == bid);
		authorDao.saveAuthor(author);
	}

}
