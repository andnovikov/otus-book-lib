package ru.anovikov.learning.otusbooklib.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.rest.GenreController;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    private static final String FIELD_INS_ID = "123";
    private static final String FIELD_INS_GENRENAME = "genrename";

    @Autowired
    private MockMvc mvc;

    @MockBean
    GenreService genreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getGenres() throws Exception{
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(FIELD_INS_ID, FIELD_INS_GENRENAME));
        when(genreService.getAll()).thenReturn(genres);

        mvc.perform(get("/api/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(genres)));
        verify(genreService).getAll();
    }

    @Test
    public void getGenre() throws Exception{
        Genre genre = new Genre(FIELD_INS_ID, FIELD_INS_GENRENAME);
        when(genreService.findById(FIELD_INS_ID)).thenReturn(genre);

        mvc.perform(get("/api/genre/" + FIELD_INS_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(genre)));
        verify(genreService).findById(FIELD_INS_ID);
    }
}