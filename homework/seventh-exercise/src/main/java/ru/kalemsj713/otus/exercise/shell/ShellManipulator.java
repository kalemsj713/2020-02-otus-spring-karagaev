package ru.kalemsj713.otus.exercise.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.kalemsj713.otus.exercise.repository.AuthorDao;
import ru.kalemsj713.otus.exercise.repository.BookDao;
import ru.kalemsj713.otus.exercise.repository.CommentDao;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Comment;
import ru.kalemsj713.otus.exercise.domain.Genre;
import ru.kalemsj713.otus.exercise.repository.GenreDao;

@ShellComponent
@AllArgsConstructor
public class ShellManipulator {

	private final AuthorDao authorDao;
	private final BookDao bookDao;
	private final CommentDao commentDao;
	private final GenreDao genreDao;


	@ShellMethod(value = "add new author", key = {"aa"})
	public void addAuthor(@ShellOption() String name) {
		authorDao.save(new Author(name));
	}

	@ShellMethod(value = "save author ", key = {"sa"})
	public void saveAuthor(@ShellOption() long id, @ShellOption() String name) {
		Author author = authorDao.findById(id).orElseThrow();
		author.setName(name);
		authorDao.save(author);
	}

	@ShellMethod(value = "delete author ", key = {"da"})
	public void deleteAuthor(@ShellOption() long id) {
		authorDao.deleteById(id);
	}

	@ShellMethod(value = "get author", key = {"ga"})
	public void getAuthor(@ShellOption() long id) {
		System.out.println(authorDao.findAuthorById(id).orElseThrow());
	}


	@ShellMethod(value = "add new genre", key = {"ag"})
	public void addGenre(@ShellOption() String title) {
		genreDao.save(new Genre(title));
	}

	@ShellMethod(value = "save genre ", key = {"sg"})
	public void saveGenre(@ShellOption() long id, @ShellOption() String title) {
		Genre genre = genreDao.findById(id).orElseThrow();
		genre.setTitle(title);
		genreDao.save(genre);
	}

	@ShellMethod(value = "delete genre ", key = {"dg"})
	public void deleteGenre(@ShellOption() long id) {
		genreDao.deleteById(id);
	}

	@ShellMethod(value = "get genre", key = {"gg"})
	public void getGenre(@ShellOption() long id) {
		System.out.println(genreDao.findGenreById(id).orElseThrow());
	}

	@ShellMethod(value = "add new book", key = {"ab"})
	public void addBook(@ShellOption() String title) {
		Book book = new Book();
		book.setId(0L);
		book.setTitle(title);
		bookDao.save(new Book(title));
	}

	@ShellMethod(value = "save book ", key = {"sb"})
	public void saveBook(@ShellOption() long id, @ShellOption() String title) {
		Book book = bookDao.findById(id).orElseThrow();
		book.setTitle(title);
		bookDao.save(book);
	}

	@ShellMethod(value = "delete book ", key = {"db"})
	public void deleteBook(@ShellOption() long id) {
		bookDao.deleteById(id);
	}

	@ShellMethod(value = "get book", key = {"gb"})
	public void getBook(@ShellOption() long id) {
		System.out.println(bookDao.findBookById(id).orElseThrow().toStringInformative());
	}

	@ShellMethod(value = "get book count", key = {"gbc"})
	public void getBookCount() {
		System.out.println(bookDao.count());
	}

	@ShellMethod(value = "get allBook", key = {"gba"})
	public void getAllBooks() {
		System.out.println(bookDao.findAll());
	}


	@ShellMethod(value = "add new comment", key = {"ac"})
	public void addComment(@ShellOption() String text, @ShellOption() long bookId) {
		Comment comment = new Comment(text);
		comment.setBook(bookDao.findById(bookId).orElseThrow());
		commentDao.save(comment);
	}

	@ShellMethod(value = "save comment ", key = {"sc"})
	public void saveComment(@ShellOption() long id, @ShellOption() String text) {
		Comment comment = commentDao.findById(id).orElseThrow();
		comment.setText(text);
		commentDao.save(comment);
	}

	@ShellMethod(value = "delete comment ", key = {"dc"})
	public void deleteComment(@ShellOption() long id) {
		commentDao.deleteById(id);
	}

	@ShellMethod(value = "get comment", key = {"gc"})
	public void getComment(@ShellOption() long id) {
		System.out.println(commentDao.findById(id));
	}

	@ShellMethod(value = "get comments book", key = {"gcb"})
	public void getComments(@ShellOption() long id) {
		System.out.println(commentDao.findAllByBookId(id));
	}

	@ShellMethod(value = "add genre book", key = {"agb"})
	public void addGenreToBook(@ShellOption() long bid, @ShellOption() long gid) {
		Book book = bookDao.findById(bid).orElseThrow();
		Genre genre = genreDao.findById(gid).orElseThrow();
		genre.getBooks().add(book);
		genreDao.save(genre);
	}

	@ShellMethod(value = "add author book", key = {"aab"})
	public void addAuthorToBook(@ShellOption() long bid, @ShellOption() long aid) {
		Book book = bookDao.findById(bid).orElseThrow();
		Author author = authorDao.findById(aid).orElseThrow();
		author.getBooks().add(book);
		authorDao.save(author);
	}

	@ShellMethod(value = "delete genre book", key = {"dgb"})
	public void deleteGenreToBook(@ShellOption() long bid, @ShellOption() long gid) {
		Genre genre = genreDao.findById(gid).orElseThrow();
		genre.getBooks().removeIf(b -> b.getId() == bid);
		genreDao.save(genre);
	}

	@ShellMethod(value = "delete author book", key = {"dab"})
	public void deleteAuthorToBook(@ShellOption() long bid, @ShellOption() long aid) {
		Author author = authorDao.findById(aid).orElseThrow();
		author.getBooks().removeIf(b -> b.getId() == bid);
		authorDao.save(author);
	}

}
