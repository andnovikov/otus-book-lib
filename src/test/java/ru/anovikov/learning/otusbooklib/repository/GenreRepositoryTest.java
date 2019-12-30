package ru.anovikov.learning.otusbooklib.repository;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository for genre")
@RunWith(SpringRunner.class)
@DataMongoTest
class GenreRepositoryTest {

    private static final String FIELD_INS_GENRENAME = "genre1";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_GENRENAME = "genre2";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private GenreRepository genreRepository;

    private Genre genre;

    @Before
    public void init(){
        genre = genreRepository.save(new Genre(FIELD_INS_GENRENAME));
    }

    @Test
    void shouldSaveAndLoadCorrectGenre() {
        assertThat(genre.getGenreName()).isEqualTo(FIELD_INS_GENRENAME);
    }

    @Test
    void shouldDeleteGenre() {
        genreRepository.delete(genre);
        assertThat(genreRepository.findById(genre.getId())).isNotPresent();
    }

}