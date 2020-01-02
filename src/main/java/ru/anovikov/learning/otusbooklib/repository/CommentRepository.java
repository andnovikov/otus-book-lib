package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    void delete(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findByBook(Book book);

    List<Comment> findAll();

}
