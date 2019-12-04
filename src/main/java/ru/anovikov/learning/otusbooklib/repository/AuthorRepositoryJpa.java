package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            em.flush();
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Optional<Author> author = findById(id);
        em.remove(author);
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        try {
            TypedQuery<Author> query = em.createNamedQuery("Author.getByName", Author.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return query.getSingleResult();
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from author a",
                Author.class);
        return query.getResultList();
    }
}
