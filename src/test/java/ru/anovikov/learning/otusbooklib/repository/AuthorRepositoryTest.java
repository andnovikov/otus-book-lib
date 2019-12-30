package ru.anovikov.learning.otusbooklib.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Author;

@DisplayName("Repository for authors")
@RunWith(SpringRunner.class)
@DataMongoTest
class AuthorRepositoryTest {

    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    private static final long FIELD_UPD_ID = 2;
    private static final String FIELD_UPD_FIRSTNAME = "firstname1";
    private static final String FIELD_UPD_LASTNAME = "lastname1";

    private static final long FIELD_DEL_ID = 3;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldSaveAndLoadCorrectAuthor() {
        Author author = new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        author = authorRepository.save(author);
        assertThat(authorRepository.findById(author.getId())).get()
                .hasFieldOrPropertyWithValue("firstName", FIELD_INS_FIRSTNAME);
    }

    @Test
    void shouldUpateAuthor() {
        Author author = new Author(FIELD_UPD_ID, FIELD_UPD_FIRSTNAME, FIELD_UPD_LASTNAME);
        authorRepository.save(author);
        assertThat(authorRepository.findById(FIELD_UPD_ID)).get()
                .hasFieldOrPropertyWithValue("firstName", FIELD_UPD_FIRSTNAME);
    }

    @Test
    void shouldDeleteAuthor() {
        Author author = authorRepository.findById(FIELD_DEL_ID).get();
        authorRepository.delete(author);
        assertThat(authorRepository.findById(FIELD_DEL_ID)).isNotPresent();
    }
}