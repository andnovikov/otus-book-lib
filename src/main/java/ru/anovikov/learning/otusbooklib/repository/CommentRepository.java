package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    void delete(long id);

    Comment findById(long id);

    List<Comment> findByBook(long bookId);

    List<Comment> getAll();

}
