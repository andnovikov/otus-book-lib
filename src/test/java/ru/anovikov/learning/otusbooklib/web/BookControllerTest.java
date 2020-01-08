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
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.rest.BookController;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final String FIELD_INS_ID = "123";
    private static final String FIELD_INS_TITLE = "title";

    @Autowired
    MockMvc mvc;

    @MockBean
    BookService bookService;

    @MockBean
    GenreService genreService;

    @MockBean
    AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getBooks() throws Exception{
        List<Book> books = new ArrayList<>();
        books.add(new Book(FIELD_INS_ID, null, null, FIELD_INS_TITLE));
        when(bookService.getAll()).thenReturn(books);

        mvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
        verify(bookService).getAll();
    }

    @Test
    public void getBook() throws Exception {
        Book book = new Book(FIELD_INS_ID, null, null, FIELD_INS_TITLE);
        when(bookService.findById(FIELD_INS_ID)).thenReturn(book);

        mvc.perform((get("/api/book/" + FIELD_INS_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
        verify(bookService).findById(FIELD_INS_ID);
    }
}