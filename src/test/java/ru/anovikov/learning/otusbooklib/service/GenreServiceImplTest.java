package ru.anovikov.learning.otusbooklib.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.GenreRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreServiceImplTest {

    public static final long FIELD_ID = 1;
    public static final String FIELD_GENRENAME = "genre3";

    @InjectMocks
    GenreServiceImpl genreService;

    @Mock
    GenreRepository genreRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(FIELD_GENRENAME);
        when(genreRepository.findByGenreName(anyString())).thenReturn();

        assertThrows(DuplicateValueException.class, () -> {
            genreService.insert(FIELD_GENRENAME);
        });
    }
/*
    @Test
    void shouldCheckDuplicateInsertGenre() {
        Genre genre = new Genre(FIELD_GENRENAME);
        when(genreRepository.findByGenreName(anyString())).thenReturn(genre);

        assertThrows(DuplicateValueException.class, () -> {
            genreService.update(FIELD_ID, FIELD_GENRENAME);
        });
    }
    */
}