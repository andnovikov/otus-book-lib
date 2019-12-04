package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspections")
@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            em.flush();
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        findById(id);
/*
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );

 */
    };

    @Override
    public Optional<Book> findById(long id) {
        try {
            //TODO getById
            /*
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
            */
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
        return null;
    };

    @Override
    public Optional<Book> findByTitle(String title) {
        try {
            // TODO getByTitle
            /*
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

             */
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
        return null;
    };

    @Override
    public Optional<Book> findByParam(long authorId, long genreId, String title) {
        try {
            //TODO getByParam
            /*
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

             */
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
        return null;
    };

    @Override
    public List<Book> getAll() {
        /*
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

         */
        return null;
    };
}
