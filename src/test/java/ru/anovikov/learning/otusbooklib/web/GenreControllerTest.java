package ru.anovikov.learning.otusbooklib.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    private static final String FIELD_INS_ID = "123";
    private static final String FIELD_INS_GENRENAME = "genrename";

    @Autowired
    private MockMvc mvc;

    @MockBean
    GenreService genreService;

    @Test
    public void getGenres() throws Exception{
        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres"))
                .andExpect(model().attributeExists("genres"));
        verify(genreService).getAll();
    }

    @Test
    public void getGenre() throws Exception{
        when(genreService.findById(FIELD_INS_ID)).thenReturn(new Genre(FIELD_INS_ID, FIELD_INS_GENRENAME));
        mvc.perform(get("/genre/" + FIELD_INS_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("genre"))
                .andExpect(model().attributeExists("genre"));
        verify(genreService).findById(FIELD_INS_ID);
    }
}