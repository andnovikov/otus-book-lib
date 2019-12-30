package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository for books")
@RunWith(SpringRunner.class)
@DataMongoTest
class BookRepositoryTest {

    private static final String FIELD_INS_GENREID = "1";
    private static final String FIELD_INS_AUTHORID = "1";
    private static final String FIELD_INS_TITLE = "book1";

    private static final String FIELD_UPD_ID = "1";
    private static final String FIELD_UPD_GENREID = "1";
    private static final String FIELD_UPD_AUTHORID = "1";
    private static final String FIELD_UPD_TITLE = "book2";

    private static final String FIELD_DEL_ID = "2";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @org.testng.annotations.Test
    void shouldSaveAndLoadCorrectBook() {
        Author author = authorRepository.findById(FIELD_INS_AUTHORID).get();
        Genre genre = genreRepository.findById(FIELD_INS_GENREID).get();
        Book book = new Book(author, genre, FIELD_INS_TITLE);
        bookRepository.save(book);
        assertThat(bookRepository.findById(book.getId())).get()
                .hasFieldOrPropertyWithValue("title", FIELD_INS_TITLE);
    }

    @Test
    void shouldUpdateBook() {
        Author author = authorRepository.findById(FIELD_UPD_AUTHORID).get();
        Genre genre = genreRepository.findById(FIELD_UPD_GENREID).get();
        Book book = new Book(FIELD_UPD_ID, author, genre, FIELD_UPD_TITLE);
        bookRepository.save(book);
        assertThat(bookRepository.findById(FIELD_UPD_ID)).get()
                .hasFieldOrPropertyWithValue("title", FIELD_UPD_TITLE);
    }

    @Test
    void shouldDeleteBook() {
        Book book = bookRepository.findById(FIELD_DEL_ID).get();
        bookRepository.delete(book);
        assertThat(bookRepository.findById(FIELD_DEL_ID)).isNotPresent();
    }

}