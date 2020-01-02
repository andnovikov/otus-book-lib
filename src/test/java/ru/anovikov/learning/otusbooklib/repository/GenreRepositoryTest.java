package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository for genre")
@RunWith(SpringRunner.class)
@DataJpaTest
class GenreRepositoryTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        Genre genre = new Genre(FIELD_INS_GENRENAME);
        genreRepository.save(genre);
        assertThat(genreRepository.findById(genre.getId())).get()
                .hasFieldOrPropertyWithValue("genreName", FIELD_INS_GENRENAME);
    }

    @Test
    void shouldUpdateGenre() {
        Genre genre = new Genre(FIELD_UPD_ID, FIELD_UPD_GENRENAME);
        genreRepository.save(genre);
        assertThat(genreRepository.findById(FIELD_UPD_ID)).get()
                .hasFieldOrPropertyWithValue("genreName", FIELD_UPD_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        Genre genre = genreRepository.findById(FIELD_DEL_ID).get();
        genreRepository.delete(genre);
        assertThat(genreRepository.findById(FIELD_DEL_ID)).isNotPresent();
    }
}