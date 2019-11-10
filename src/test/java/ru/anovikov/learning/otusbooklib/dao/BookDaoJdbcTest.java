package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookDaoJdbcTest {

    private static final long FIELD_INS_ID = 100000;
    private static final long FIELD_INS_GENREID = 1;
    private static final long FIELD_INS_AUTHORID = 1;
    private static final String FIELD_INS_TITLE = "book1";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectBooj() {
        Book book = new Book(FIELD_INS_ID, FIELD_INS_AUTHORID, FIELD_INS_GENREID, FIELD_INS_TITLE);
        bookDaoJdbc.insert(book);
        assertThat(bookDaoJdbc.getById(FIELD_INS_ID))
                .hasFieldOrPropertyWithValue("title", FIELD_INS_TITLE);
    }

    @Test
    void shouldUpdateBook() {
        // TODO: write test
        /*bookDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(bookDaoJdbc.getById(FIELD_DEL_ID)).isNull();*/
    }

    @Test
    void shouldDeleteBook() {
        bookDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(bookDaoJdbc.getById(FIELD_DEL_ID)).isNull();
    }
}