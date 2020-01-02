package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.Before;
import org.junit.Test;
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
public class BookRepositoryTest {

    private static final String FIELD_INS_TITLE = "book1";
    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";
    private static final String FIELD_INS_GENRENAME = "genre";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Author author;
    private Book book;
    private Genre genre;

    @Before
    public void init(){
        author = authorRepository.save(new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME));
        genre = genreRepository.save(new Genre(FIELD_INS_GENRENAME));
        book = bookRepository.save(new Book(author, genre, FIELD_INS_TITLE));
    }

    @org.testng.annotations.Test
    public void shouldSaveAndLoadCorrectBook() {
        assertThat(book.getTitle()).isEqualTo(FIELD_INS_TITLE);
    }

    @Test
    public void shouldDeleteBook() {
        bookRepository.delete(book);
        assertThat(bookRepository.findById(book.getId())).isNotPresent();
    }

}