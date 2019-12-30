package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository for comments")
@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentRepositoryTest {

    private static final String FIELD_INS_COMMENTTEXT = "comment1";
    private static final String FIELD_INS_TITLE = "book1";
    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";
    private static final String FIELD_INS_GENRENAME = "genre";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment;
    private Author author;
    private Book book;
    private Genre genre;

    @Before
    public void init(){
        author = authorRepository.save(new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME));
        genre = genreRepository.save(new Genre(FIELD_INS_GENRENAME));
        book = bookRepository.save(new Book(author, genre, FIELD_INS_TITLE));
        comment = commentRepository.save(new Comment(book, FIELD_INS_COMMENTTEXT));
    }

    @Test
    public void shouldSaveAndLoadCorrectComment() {
        assertThat(comment.getCommentText()).isEqualTo(FIELD_INS_COMMENTTEXT);
    }

    @Test
    public void shouldDeleteComment() {
        commentRepository.delete(comment);
        assertThat(commentRepository.findById(comment.getId())).isNotPresent();
    }

}