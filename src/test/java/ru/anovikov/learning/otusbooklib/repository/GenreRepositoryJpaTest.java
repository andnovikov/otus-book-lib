package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GenreRepositoryJpaTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    public static final String FIELD_INSDUP_GENRENAME = "genre3";
    public static final String FIELD_UPDDUP_GENRENAME = "genre5";

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_GENRENAME);
        genreRepositoryJpa.insert(genre);
        assertThat(genreRepositoryJpa.getById(genre.getId()))
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(FIELD_UPD_ID, FIELD_UPD_GENRENAME);
        genreRepositoryJpa.update(genre);
        assertThat(genreRepositoryJpa.getById(FIELD_UPD_ID))
                .hasFieldOrPropertyWithValue("genreName", FIELD_UPD_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        genreRepositoryJpa.delete(FIELD_DEL_ID);
        assertThrows(NoDataFoundException.class, () -> {
            genreRepositoryJpa.getById(FIELD_DEL_ID);});
    }

    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(0, FIELD_INSDUP_GENRENAME);
        genre = genreRepositoryJpa.insert(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreRepositoryJpa.insert(new Genre(0, FIELD_INSDUP_GENRENAME));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateGenre() {
        Genre genre = new Genre(0, FIELD_UPDDUP_GENRENAME);
        genre = genreRepositoryJpa.insert(genre);
        assertThrows(DuplicateValueException.class, () -> {
            genreRepositoryJpa.update(new Genre(FIELD_UPD_ID, FIELD_UPDDUP_GENRENAME));
        });
    }
}