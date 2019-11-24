package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.anovikov.learning.otusbooklib.domain.Author;

import javax.xml.soap.Node;
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
    public Author insert(Author author){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());

        // check if exists by name
        try {
            Author chkAuthor = getByName(author.getFirstName(), author.getLastName());
            if (chkAuthor != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }

        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into author (firstName, lastName) values (:firstName, :lastName)", params, kh);
        author.setId(kh.getKey().longValue());
        return author;
    }

    @Override
    public void update(Author author, long id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());

        // check if exists by id
        getById(id);
        try {
            // check if exists by name
            Author chkAuthor = getByName(author.getFirstName(), author.getLastName());
            if (chkAuthor != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }

        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("update author set firstName = :firstName, lastName = :lastName where id = :id", params, kh);
    }

    @Override
    public void deleteById(long id){
        // check if exists by id
        getById(id);

        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from author where id = :id", params
        );
    }

    @Override
    public Author getById(long id){
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            Author author = namedParameterJdbcOperations.queryForObject(
                        "select id, firstName, lastName from author where id = :id", params, new AuthorMapper());
            return author;
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
    }

    @Override
    public Author getByName(String firstName, String lastName){
        Author author;
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("firstName", firstName);
            params.addValue("lastName", lastName);
            author = namedParameterJdbcOperations.queryForObject(
                    "select id, firstName, lastName from author where firstName = :firstName and lastName = :lastName", params, new AuthorMapper()
            );
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
        return author;
    }

    @Override
    public List<Author> getAll(){
        return namedParameterJdbcOperations.query("select id, firstName, lastName from author", new AuthorMapper());
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
