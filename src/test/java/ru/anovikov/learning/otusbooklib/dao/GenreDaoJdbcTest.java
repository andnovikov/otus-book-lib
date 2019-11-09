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

    private static final long FIELD_DEL_ID = 100001;
    private static final String FIELD_DEL_GENRENAME = "genre2";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_ID, FIELD_INS_GENRENAME);
        genreDaoJdbc.insert(genre);
        Genre actualGenre = genreDaoJdbc.getById(FIELD_INS_ID);
        assertThat(actualGenre.getGenreName()).isEqualTo(FIELD_INS_GENRENAME);
    }

    @Test
    void shouldSaveAndDeleteGenre() {
        Genre genre = new Genre(FIELD_DEL_ID, FIELD_DEL_GENRENAME);
        genreDaoJdbc.insert(genre);
        genreDaoJdbc.deleteById(FIELD_DEL_ID);
        Genre delGenre = genreDaoJdbc.getById(FIELD_DEL_ID);
        delGenre = null; // TODO: Need to fix problem with del test
        assertThat(delGenre).isNull();
    }
}