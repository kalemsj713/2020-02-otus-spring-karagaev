package ru.kalemsj713.otus.exercise.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.kalemsj713.otus.exercise.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository

public class GenreDaoImpl implements GenreDao {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}


	@Override
	public Genre getGenre(Long id) {
		Map<String, Long> params = Collections.singletonMap("id", id);
		try {
			return namedParameterJdbcOperations.queryForObject(
					"select * from genre where id = :id", params, new GenreDaoImpl.GenreMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void deleteGenre(Genre genre) {
		Map<String, Long> params = Collections.singletonMap("id", genre.getId());
		namedParameterJdbcOperations.update("delete from genre where id = :id", params);
	}

	@Override
	public void createGenre(Genre genre) {
		Map<String, String> params = Collections.singletonMap("title", genre.getTitle());
		namedParameterJdbcOperations.update("insert into genre (title) values (:title)", params);
	}


	private static class GenreMapper implements RowMapper<Genre> {
		@Override
		public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
			long id = resultSet.getLong("id");
			String title = resultSet.getString("title");
			return new Genre(id, title);
		}
	}


}
