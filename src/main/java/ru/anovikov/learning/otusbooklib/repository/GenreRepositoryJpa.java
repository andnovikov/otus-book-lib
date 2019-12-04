package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Genre save(Genre genre){
        if (genre.getId() <= 0) {
            em.persist(genre);
            em.flush();
            return  genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public void delete(long id) {
        Genre genre = findById(id);
        em.remove(genre);
    }

    @Override
    public Genre findById(long id){
        Optional<Genre> foundEntity = Optional.ofNullable(em.find(Genre.class, id));
        if (!foundEntity.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundEntity.get();
    }

    @Override
    public Genre findByName(String genreName) {
        try {
            TypedQuery<Genre> query = em.createNamedQuery("Genre.findByName", Genre.class);
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
