package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import ru.anovikov.learning.otusbooklib.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("authorId", book.getAuthorId());
        params.addValue("genreId", book.getGenreId());
        params.addValue("title", book.getTitle());
        namedParameterJdbcOperations.update("insert into books (id, authorId, genreId, title) values (:id, :authorId, :genreId, :title)", params);
    };

    @Override
    public Book getById(long id) {
        Book book = null;
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            book = namedParameterJdbcOperations.queryForObject(
                    "select id, authorId, genreId, title from books where id = :id", params, new BookMapper()
            );
        }
        catch (EmptyResultDataAccessException e) {
            book = null;
        }

        return book;
    };

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, authorId, genreId, title from books", new BookMapper());
    };

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    };

    private static class BookMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("authorId");
            long genreId = resultSet.getLong("genreId");
            String title = resultSet.getString("title");
            return new Book(id, authorId, genreId, title);
        }
    }
}
