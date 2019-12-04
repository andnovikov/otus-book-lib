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
        Book book = findById(id);
        em.remove(book);
    };

    @Override
    public Book findById(long id) {
        Optional<Book> foundEntity = Optional.ofNullable(em.find(Book.class, id));
        if (!foundEntity.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundEntity.get();
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
