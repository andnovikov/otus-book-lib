package ru.anovikov.learning.otusbooklib.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.service.AuthorService;
import ru.anovikov.learning.otusbooklib.service.BookService;
import ru.anovikov.learning.otusbooklib.service.GenreService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void getBooks() throws Exception{
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"));
        verify(bookService).getAll();
    }

    @Test
    public void getBook() throws Exception {
        when(bookService.findById(FIELD_INS_ID)).thenReturn(new Book(FIELD_INS_ID, null, null, FIELD_INS_TITLE));
        mvc.perform((get("/book/" + FIELD_INS_ID)))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().attributeExists("book"));
        verify(bookService).findById(FIELD_INS_ID);
    }
}