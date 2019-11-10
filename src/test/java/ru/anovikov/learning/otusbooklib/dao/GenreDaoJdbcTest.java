package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GenreDaoJdbcTest {


    private static final long FIELD_INS_ID = 100000;
    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_ID, FIELD_INS_GENRENAME);
        genreDaoJdbc.insert(genre);
        assertThat(genreDaoJdbc.getById(FIELD_INS_ID))
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        // TODO: write test
        /*genreDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(genreDaoJdbc.getById(FIELD_DEL_ID)).isNull();*/
    }

    @Test
    void shouldDeleteGenre() {
        genreDaoJdbc.deleteById(FIELD_DEL_ID);
        assertThat(genreDaoJdbc.getById(FIELD_DEL_ID)).isNull();
    }
}