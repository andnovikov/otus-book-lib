package ru.anovikov.learning.otusbooklib.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.GenreRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class GenreServiceImplTest {

    private static final String FIELD_INS_ID = "123";
    private static final String FIELD_UPD_ID = "456";
    public static final String FIELD_GENRENAME = "genre3";

    @InjectMocks
    GenreServiceImpl genreService;

    @Mock
    GenreRepository genreRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(FIELD_GENRENAME);
        when(genreRepository.findByGenreName(anyString())).thenReturn(Optional.of(genre));
        assertThrows(DuplicateValueException.class, () -> {
            genreService.insert(new Genre(FIELD_GENRENAME));
        });
    }

    @Test
    void shouldCheckDuplicateUpdateGenre() {
        Genre genre = new Genre(FIELD_INS_ID, FIELD_GENRENAME);
        when(genreRepository.findByGenreName(anyString())).thenReturn(Optional.of(genre));
        when(genreRepository.existsById(anyString())).thenReturn(true);

        assertThrows(DuplicateValueException.class, () -> {
            genreService.update(FIELD_UPD_ID, FIELD_GENRENAME);
        });
    }
}