package ru.anovikov.learning.otusbooklib.repository;

import com.sun.javafx.collections.MappingChange;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Genre insert(Genre genre){
        // check if exists by name
        try {
            Genre chkGenre = getByName(genre.getGenreName());
            if (chkGenre != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }

        em.persist(genre);
        // TODO return
        // em.refresh(genre);
        return genre;
    }

    @Override
    public void update(Genre genre){
        // check if exists by id
        getById(genre.getId());
        try {
            // check if exists by name
            Genre chkGenre = getByName(genre.getGenreName());
            if ((chkGenre != null) && (genre.getId() != chkGenre.getId())) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // nothing
        }
        em.merge(genre);
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Genre genre = getById(id);
        em.remove(genre);
    }

    @Override
    public Genre getById(long id){
        try {
            return em.find(Genre.class, id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoDataFoundException();
        }
    }

    @Override
    public Genre getByName(String genreName) {
        try {
            TypedQuery<Genre> query = em.createNamedQuery("Genre.getByName", Genre.class);
            query.setParameter("genreName", genreName);
            return query.getSingleResult();
        }
        catch (EmptyResultDataAccessException | NoResultException e) {
            throw new NoDataFoundException();
        }
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createNamedQuery("Genre.findAll", Genre.class);
        return query.getResultList();
    }
}
