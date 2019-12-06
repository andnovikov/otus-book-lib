package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;
import ru.anovikov.learning.otusbooklib.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment insert(Book book, String commentText) {
        // can save any comment
        Comment comment = new Comment(book, commentText);
        comment = commentRepository.save(comment);
        return comment;
    }

    @Override
    public void delete(long id) {
        Optional<Comment> foundComment = commentRepository.findById(id);
        if (!foundComment.isPresent()) {
            throw new NoDataFoundException();
        }
        commentRepository.delete(foundComment.get());
    }

    @Override
    public Comment findById(long id) {
        Optional<Comment> foundComment = commentRepository.findById(id);
        if (!foundComment.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundComment.get();
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return commentRepository.findByBook(book);
    }
}
