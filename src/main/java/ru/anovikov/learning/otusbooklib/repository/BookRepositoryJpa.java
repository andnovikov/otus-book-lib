package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
            return book;
        } else {
            em.merge(book);
            return book;
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Book book = findById(id);
        em.remove(book);
    };

    @Override
    public Book findById(long id) {
        try {
            TypedQuery<Book> query = em.createNamedQuery("Book.findById", Book.class);
            query.setParameter("bookId", id);
            return query.getSingleResult();
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
    };

    @Override
    public Book findByTitle(String title) {
        try {
            TypedQuery<Book> query = em.createNamedQuery("Book.findByTitle", Book.class);
            query.setParameter("title", title);
            return query.getSingleResult();
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
    };

    @Override
    public Book findByParam(long authorId, long genreId, String title) {
        try {
            TypedQuery<Book> query = em.createNamedQuery("Book.findByParam", Book.class);
            query.setParameter("authorId", authorId);
            query.setParameter("genreId", genreId);
            query.setParameter("title", title);
            return query.getSingleResult();
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
    };

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
        return query.getResultList();
    };
}
