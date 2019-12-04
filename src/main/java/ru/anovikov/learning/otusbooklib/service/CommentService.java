package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

public interface CommentService {

    Comment insert(Book book, String commentText);

    void delete(long id);

    Comment findById(long id);

    Comment findByBook(long bookId);

}
