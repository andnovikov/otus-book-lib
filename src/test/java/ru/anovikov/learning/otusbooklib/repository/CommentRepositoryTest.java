package ru.anovikov.learning.otusbooklib.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository for comments")
@RunWith(SpringRunner.class)
@DataJpaTest
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

    @Test
    void shouldSaveAndLoadCorrectComment() {
        Book book = bookRepository.findById(FIELD_INS_BOOKID).get();
        Comment comment = new Comment(book, FIELD_INS_COMMENTTEXT);
        comment = commentRepository.save(comment);
        assertThat(commentRepository.findById(comment.getId())).get()
                .hasFieldOrPropertyWithValue("commentText", FIELD_INS_COMMENTTEXT);
    }

    @Test
    void shouldDeleteComment() {
        Comment comment = commentRepository.findById(FIELD_DEL_ID).get();
        commentRepository.delete(comment);
        assertThat(commentRepository.findById(FIELD_DEL_ID)).isNotPresent();
    }
}