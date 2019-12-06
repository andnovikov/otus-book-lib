package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspections")
@Repository
@Transactional
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
            em.merge(comment);
            em.flush();
            return comment;
        }
    }

    @Override
    public void delete(long id) {
        // check if exists by id
        Comment comment = findById(id);
        em.remove(comment);
        em.flush();
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
    public List<Comment> findByBook(Book book) {
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByBook", Comment.class);
        query.setParameter("bookId", book.getId());
        return query.getResultList();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);
        return query.getResultList();
    }
}
