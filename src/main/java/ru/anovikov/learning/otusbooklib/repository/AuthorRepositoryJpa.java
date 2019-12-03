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
    public Author insert(Author author) {
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
        em.persist(author);
        return author;
    }

    @Override
    public void update(Author author) {
        // check if exists by id
        getById(author.getId());
        try {
            // check if exists by name
            Author chkAuthor = getByName(author.getFirstName(), author.getLastName());
            if ((chkAuthor != null) && (author.getId() != chkAuthor.getId())) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }
        em.merge(author);
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Author author = getById(id);
        em.remove(author);
    }

    @Override
    public Author getById(long id) {
        try {
            return em.find(Author.class, id);
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
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
