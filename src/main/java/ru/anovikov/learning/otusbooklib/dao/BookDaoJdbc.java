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
    public Book insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", book.getAuthor().getId());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("title", book.getTitle());

        // check if exists by name
        try {
            Book chkBook = getByParam(book.getAuthor().getId(), book.getGenre().getId(), book.getTitle());
            if (chkBook != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }

        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book (authorId, genreId, title) values (:authorId, :genreId, :title)", params, kh);
        book.setId(kh.getKey().longValue());
        return book;
    };

    @Override
    public Book update(Book book, Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("authorId", book.getAuthor().getId());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("title", book.getTitle());

        // check if exists by id
        getById(id);
        try {
            Book chkBook = getByParam(book.getAuthor().getId(), book.getGenre().getId(), book.getTitle());
            if (chkBook != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }

        namedParameterJdbcOperations.update("update book set authorId = :authorId, genreId = :genreId, title = :title where id = :id", params);

        return getById(id);
    };

    @Override
    public void deleteById(long id) {
        // check if exists by id
        getById(id);

        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    };

    @Override
    public Book getById(long id) {
        try {
            Map<String, Object> params = Collections.singletonMap("bookId", id);
            Book book = namedParameterJdbcOperations.queryForObject("select b.id        as bookId, " +
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
            return book;
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
    };

    @Override
    public Book getByTitle(String title) {
        try {
            Map<String, Object> params = Collections.singletonMap("title", title);
            Book book = namedParameterJdbcOperations.queryForObject("select b.id        as bookId, " +
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
                                                                       " where b.title = :title", params, new BookMapper());
            return book;
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
    };

    @Override
    public Book getByParam(long authorId, long genreId, String title) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("authorId", authorId);
            params.addValue("genreId", genreId);
            params.addValue("title", title);

            Book book = namedParameterJdbcOperations.queryForObject("select b.id        as bookId, " +
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
                            " where b.authorId = :authorId" +
                            "   and b.genreId  = :genreId" +
                            "   and b.title    = :title",
                    params, new BookMapper());
            return book;
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
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
