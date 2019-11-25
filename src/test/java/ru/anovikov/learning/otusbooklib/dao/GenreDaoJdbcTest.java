package ru.anovikov.learning.otusbooklib.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GenreDaoJdbcTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    public static final String FIELD_INSDUP_GENRENAME = "genre3";
    public static final String FIELD_UPDDUP_GENRENAME = "genre5";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(0, FIELD_INS_GENRENAME);
        genreDaoJdbc.insert(genre);
        assertThat(genreDaoJdbc.getById(genre.getId()))
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(FIELD_UPD_ID, FIELD_UPD_GENRENAME);
        genreDaoJdbc.update(genre);
        assertThat(genreDaoJdbc.getById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("genreName", FIELD_UPD_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        genreDaoJdbc.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {genreDaoJdbc.getById(FIELD_DEL_ID);});
    }

    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(0, FIELD_INSDUP_GENRENAME);
        genre = genreDaoJdbc.insert(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreDaoJdbc.insert(new Genre(0, FIELD_INSDUP_GENRENAME));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateGenre() {
        Genre genre = new Genre(0, FIELD_UPDDUP_GENRENAME);
        genre = genreDaoJdbc.insert(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreDaoJdbc.update(new Genre(FIELD_UPD_ID, FIELD_UPDDUP_GENRENAME));
        });
    }
}