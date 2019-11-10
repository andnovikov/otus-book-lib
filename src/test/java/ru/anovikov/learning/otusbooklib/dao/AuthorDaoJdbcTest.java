package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorDaoJdbcTest {

    private static final long FIELD_INS_ID = 100000;
    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectAuthor() {
        Author author = new Author(FIELD_INS_ID, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        authorDaoJdbc.insert(author);
        assertThat(authorDaoJdbc.getById(FIELD_INS_ID))
                .hasFieldOrPropertyWithValue("firstName", FIELD_INS_FIRSTNAME);
    }

    @Test
    void shouldUpateAuthor() {
        // TODO: write test
        /*authorDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(authorDaoJdbc.getById(FIELD_DEL_ID)).isNull();*/
    }

    @Test
    void shouldDeleteAuthor() {
        authorDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(authorDaoJdbc.getById(FIELD_DEL_ID)).isNull();
    }
}