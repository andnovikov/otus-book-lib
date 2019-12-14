package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Author;

import javax.persistence.*;
import java.util.*;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        em.setFlushMode(FlushModeType.COMMIT);
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            em.merge(author);
            return author;
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Author author = findById(id);
        em.remove(author);
    }

    @Override
    public Author findById(long id) {
        Optional<Author> foundEntity = Optional.ofNullable(em.find(Author.class, id));
        if (!foundEntity.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundEntity.get();
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        try {
            TypedQuery<Author> query = em.createNamedQuery("Author.findByName", Author.class);
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
        TypedQuery<Author> query = em.createNamedQuery("Author.findAll", Author.class);
        return query.getResultList();
    }
}
