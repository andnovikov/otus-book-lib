package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            em.flush();
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Comment comment = findById(id);
        em.remove(comment);
    }

    @Override
    public Comment findById(long id) {
        Optional<Comment> foundEntity = Optional.ofNullable(em.find(Comment.class, id));
        if (!foundEntity.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundEntity.get();
    }

    @Override
    public List<Comment> findByBook(long bookId) {
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByBook", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);
        return query.getResultList();
    }
}
