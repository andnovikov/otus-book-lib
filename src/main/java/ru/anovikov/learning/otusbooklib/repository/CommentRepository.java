package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment save(Comment comment);

    void delete(Comment comment);

    Optional<Comment> findById(String id);

    List<Comment> findByBook(Book book);

    List<Comment> findAll();

}
