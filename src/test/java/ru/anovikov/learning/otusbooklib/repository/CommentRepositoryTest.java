package ru.anovikov.learning.otusbooklib.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository JPA for comments")
@DataJpaTest
@Import({CommentRepository.class, BookRepository.class})
class CommentRepositoryTest {

    private static final long FIELD_INS_BOOKID = 1;
    private static final String FIELD_INS_COMMENTTEXT = "comment1";

    private static final long FIELD_DEL_ID = 1;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        sessionFactory.getStatistics().clear();
    }

    /*
    @Test
    void shouldSaveAndLoadCorrectComment() {
        Book book = bookRepository.findById(FIELD_INS_BOOKID);
        Comment comment = new Comment(book, FIELD_INS_COMMENTTEXT);
        comment = commentRepository.save(comment);
        assertThat(commentRepository.findById(comment.getId()))
                .hasFieldOrPropertyWithValue("commentText", FIELD_INS_COMMENTTEXT);
    }

    @Test
    void shouldDeleteComment() {
        commentRepository.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            commentRepository.findById(FIELD_DEL_ID);});
    }
    */
}