package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import java.util.List;

public interface CommentService {

    Comment insert(Book book, String commentText);

    void delete(String id);

    Comment findById(String id);

    List<Comment> findByBook(Book book);

}
