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

    private static final long FIELD_DEL_ID = 100001;
    private static final String FIELD_DEL_FIRSTNAME = "firstname";
    private static final String FIELD_DEL_LASTNAME = "lastname";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectAuthor() {
        Author author = new Author(FIELD_INS_ID, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        authorDaoJdbc.insert(author);
        Author actualAuthor = authorDaoJdbc.getById(FIELD_INS_ID);
        assertThat(actualAuthor.getLastName()).isEqualTo(author.getLastName());
    }

    @Test
    void shouldSaveAndDeleteAuthor() {
        Author author = new Author(FIELD_DEL_ID, FIELD_DEL_FIRSTNAME, FIELD_DEL_LASTNAME);
        authorDaoJdbc.insert(author);
        authorDaoJdbc.deleteById(FIELD_DEL_ID);
        Author actualAuthor = authorDaoJdbc.getById(FIELD_DEL_ID);
        assertThat(actualAuthor).isNull();
    }
}