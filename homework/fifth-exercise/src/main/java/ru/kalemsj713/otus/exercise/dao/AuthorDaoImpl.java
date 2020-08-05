package ru.kalemsj713.otus.exercise.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.kalemsj713.otus.exercise.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository

public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }


    @Override
    public Author getAuthor(Long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject("select * from author where id = :id", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        Map<String, Long> params = Collections.singletonMap("id", author.getId());
        namedParameterJdbcOperations.update("delete from author where id = :id", params);
    }

    @Override
    public void createAuthor(Author author) {
        Map<String, String> params = Collections.singletonMap("name", author.getName());
        namedParameterJdbcOperations.update("insert into author (name) values (:name)", params);
    }


    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
