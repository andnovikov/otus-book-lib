package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;
import ru.anovikov.learning.otusbooklib.repository.CommentRepository;

public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment insert(Book book, String commentText) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public Comment findByBook(long bookId) {
        return null;
    }
}
