package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public  AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into authors (id, firstName, lastName) values (:id, :firstName, :lastName)", params, kh);
    }

    @Override
    public Author getById(long id){
        Author author = null;
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            author = namedParameterJdbcOperations.queryForObject(
                    "select id, firstName, lastName from authors where id = :id", params, new AuthorMapper()
            );
        }
        catch (EmptyResultDataAccessException e) {
            author = null;
        }
        return author;
    }

    @Override
    public List<Author> getAll(){
        return namedParameterJdbcOperations.query("select id, firstName, lastName from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id){
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params
        );
    }

    @Override
    public void update(Author author, long id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("update authors set firstName = :firstName, lastName = :lastName where id = :id", params, kh);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            return new Author(id, firstName, lastName);
        }
    }

}
