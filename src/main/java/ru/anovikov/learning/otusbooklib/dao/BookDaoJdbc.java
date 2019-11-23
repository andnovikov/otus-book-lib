package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

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
        params.addValue("authorId", book.getAuthor().getId());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("title", book.getTitle());
        namedParameterJdbcOperations.update("insert into book (id, authorId, genreId, title) values (:id, :authorId, :genreId, :title)", params);
    };

    @Override
    public void update(Book book, Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("authorId", book.getAuthor().getId());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("title", book.getTitle());
        namedParameterJdbcOperations.update("update book set authorId = :authorId, genreId = :genreId, title = :title where id = :id", params);
    };

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    };

    @Override
    public Book getById(long id) {
        Book book = null;
        try {
            Map<String, Object> params = Collections.singletonMap("bookId", id);
            book = namedParameterJdbcOperations.queryForObject("select b.id        as bookId, " +
                                                                 "       a.id         as authorId," +
                                                                 "       a.firstName  as authorFirstName," +
                                                                 "       a.lastName   as authorLastName," +
                                                                 "       g.id         as genreId, " +
                                                                 "       g.genreName  as genreName, " +
                                                                 "       b.title      as bookTitle" +
                                                                 "  from book b" +
                                                                 " inner join author a" +
                                                                 "         on a.id = b.authorId" +
                                                                 " inner join genre g" +
                                                                 "         on g.id = b.genreId" +
                                                                 " where b.id = :bookId", params, new BookMapper());
        }
        catch (EmptyResultDataAccessException e) {
            book = null;
        }

        return book;
    };

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select b.id        as bookId, " +
                                                     "       a.id         as authorId," +
                                                     "       a.firstName  as authorFirstName," +
                                                     "       a.lastName   as authorLastName," +
                                                     "       g.id         as genreId, " +
                                                     "       g.genreName  as genreName, " +
                                                     "       b.title      as bookTitle" +
                                                     "  from book b" +
                                                     " inner join author a" +
                                                     "         on a.id = b.authorId" +
                                                     " inner join genre g" +
                                                     "         on g.id = b.genreId", new BookMapper());
    };

    private static class BookMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("bookId");
            long authorId = resultSet.getLong("authorId");
            String authorFirstName = resultSet.getString("authorFirstName");
            String authorLastName = resultSet.getString("authorLastName");
            long genreId = resultSet.getLong("genreId");
            String genreName = resultSet.getString("genreName");
            String bookTitle = resultSet.getString("bookTitle");

            return new Book(bookId, new Author(authorId, authorFirstName, authorLastName), new Genre(genreId, genreName), bookTitle);
        }
    }
}
