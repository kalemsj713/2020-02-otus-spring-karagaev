package ru.kalemsj713.otus.exercise.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.kalemsj713.otus.exercise.domain.Author;
import ru.kalemsj713.otus.exercise.domain.Book;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class BookDaoImpl implements BookDao {
	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public int getBooksCount() {
		return namedParameterJdbcOperations.queryForObject("select count(*) from book", new HashMap<>(), Integer.class);
	}

	@Override
	public Book getBookById(Long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		try {
			return namedParameterJdbcOperations.queryForObject("select * from book where id = :id", params, new BookMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}

	}

	public List<Book> getAll() {
		return namedParameterJdbcOperations.query("select id , title from book", new BookMapper());
	}


	@Override
	public void deleteBook(Book book) {
		Map<String, Object> params = Collections.singletonMap("id", book.getId());
		namedParameterJdbcOperations.update("delete from book where id = :id", params);
	}

	@Override
	public void createBook(String title) {
		Map<String, Object> params = Collections.singletonMap("title", title);
		namedParameterJdbcOperations.update("insert into book (title) values (:title)", params);

	}

	@Override
	public void updateBook(Book book, String newTitle) {
		Map<String, Object> params = new HashMap<>();
		params.put("title", newTitle);
		params.put("id", book.getId());
		namedParameterJdbcOperations.update("update book set  title  = (:title) where  id =(:id)", params);
	}

	@Override
	public String getBookInfo(Book selectedBook) {
		Map<String, Object> params = Collections.singletonMap("id", selectedBook.getId());
		return namedParameterJdbcOperations.query("" +
				"with authorsbooks (names, bid) as" +
				"(select     group_concat(distinct  a.name) as names ,b.id as bid from book as b  " +
				"left join books_authors as ba on b.id = ba.book_id   " +
				"left join author as a on ba.author_id = a.id group by  b.id), " +
				"	genresbooks(titles, bid) as " +
				"(select group_concat(distinct  g.title) as titles, b.id as bid from book as b " +
				"left join books_genres as bg on b.id = bg.book_id   " +
				"left join genre as g on bg.genre_id = g.id  group by b.id) " +
				" select      a.names, g.titles   from genresbooks g     " +
				"inner join authorsbooks  a on g.bid = a.bid and g.bid= (:id)  ", params, new BookInfoMapper()).get(0);
	}

	@Override
	public void addBookGenry(Book selectedBook, Genre selectedGenre) {
		Map<String, Object> params = new HashMap<>();
		params.put("book_id", selectedBook.getId());
		params.put("genre_id", selectedGenre.getId());
		namedParameterJdbcOperations.update("insert into books_genres (book_id, genre_id) values (:book_id, :genre_id)", params);
	}

	@Override
	public void addBookAuthor(Book selectedBook, Author selectedAuthor) {
		Map<String, Object> params = new HashMap<>();
		params.put("book_id", selectedBook.getId());
		params.put("author_id", selectedAuthor.getId());
		namedParameterJdbcOperations.update("insert into books_authors (book_id, author_id) values (:book_id, :author_id)", params);
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet resultSet, int i) throws SQLException {
			Long id = resultSet.getLong("id");
			String title = resultSet.getString("title");
			return new Book(id, title);
		}
	}

	private static class BookInfoMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet resultSet, int i) throws SQLException {
			String authors = resultSet.getString("names");
			String genres = resultSet.getString("titles");
			return "Автор(ы):" + authors + ", Жанр(ы):" + genres;
		}
	}
}
