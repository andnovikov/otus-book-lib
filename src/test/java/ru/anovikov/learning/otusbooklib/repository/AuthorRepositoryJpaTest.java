package ru.anovikov.learning.otusbooklib.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.anovikov.learning.otusbooklib.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Repository JPA for authors")
@DataJpaTest
@Import({AuthorRepositoryJpa.class})
class AuthorRepositoryJpaTest {

    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_FIRSTNAME = "firstname1";
    private static final String FIELD_UPD_LASTNAME = "lastname1";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

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
    void shouldSaveAndLoadCorrectAuthor() {
        Author author = new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        author = authorRepositoryJpa.save(author);
        assertThat(authorRepositoryJpa.findById(author.getId()))
                .hasFieldOrPropertyWithValue("firstName", FIELD_INS_FIRSTNAME);
    }

    @Test
    void shouldUpateAuthor() {
        Author author = new Author(FIELD_UPD_ID, FIELD_UPD_FIRSTNAME, FIELD_UPD_LASTNAME);
        authorRepositoryJpa.save(author);
        assertThat(authorRepositoryJpa.findById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("firstName", FIELD_UPD_FIRSTNAME);
    }

    @Test
    void shouldDeleteAuthor() {
        authorRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            authorRepositoryJpa.findById(FIELD_DEL_ID);});
    }
}