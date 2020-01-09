package ru.anovikov.learning.otusbooklib.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.rest.AuthorController;
import ru.anovikov.learning.otusbooklib.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    private static final String FIELD_INS_ID = "123";
    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAuthors() throws Exception{
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(FIELD_INS_ID, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME));

        when(authorService.getAll()).thenReturn(authors);
        mvc.perform(get("/api/author"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(authors)));
        verify(authorService).getAll();
    }

    @Test
    public void getAuthor() throws Exception{
        Author author = new Author(FIELD_INS_ID, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        when(authorService.findById(FIELD_INS_ID)).thenReturn(author);
        mvc.perform(get("/api/author/" + FIELD_INS_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(author)));
        verify(authorService).findById(FIELD_INS_ID);
    }
}
