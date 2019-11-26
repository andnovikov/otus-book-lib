package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthorDaoJdbcTest {

    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_FIRSTNAME = "firstname1";
    private static final String FIELD_UPD_LASTNAME = "lastname1";

    private static final long FIELD_DEL_ID = 3;

    private static final String FIELD_INSDUP_FIRSTNAME = "firstname2";
    private static final String FIELD_INSDUP_LASTNAME = "lastname2";

    private static final String FIELD_UPDDUP_FIRSTNAME = "firstname3";
    private static final String FIELD_UPDDUP_LASTNAME = "lastname3";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectAuthor() {
        Author author = new Author(0, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        author = authorDaoJdbc.insert(author);
        assertThat(authorDaoJdbc.getById(author.getId()))
                .hasFieldOrPropertyWithValue("firstName", FIELD_INS_FIRSTNAME);
    }

    @Test
    void shouldUpateAuthor() {
        Author author = new Author(FIELD_UPD_ID, FIELD_UPD_FIRSTNAME, FIELD_UPD_LASTNAME);
        authorDaoJdbc.update(author);
        assertThat(authorDaoJdbc.getById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("firstName", FIELD_UPD_FIRSTNAME);
    }

    @Test
    void shouldDeleteAuthor() {
        authorDaoJdbc.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {authorDaoJdbc.getById(FIELD_DEL_ID);});
    }

    @Test
    void shouldCheckDuplicateInsertAuthor() {
        Author author = new Author(0, FIELD_INSDUP_FIRSTNAME, FIELD_INSDUP_LASTNAME);
        author = authorDaoJdbc.insert(author);
        assertThrows(DuplicateValueException.class, () -> {
            authorDaoJdbc.insert(new Author(0, FIELD_INSDUP_FIRSTNAME, FIELD_INSDUP_LASTNAME));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateAuthor() {
        Author author = new Author(0, FIELD_UPDDUP_FIRSTNAME, FIELD_UPDDUP_LASTNAME);
        author = authorDaoJdbc.insert(author);
        assertThrows(DuplicateValueException.class, () -> {
            authorDaoJdbc.update(new Author(FIELD_UPD_ID, FIELD_UPDDUP_FIRSTNAME, FIELD_UPDDUP_LASTNAME));
        });
    }
}