package ru.anovikov.learning.otusbooklib.repository;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.anovikov.learning.otusbooklib.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

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

    private Author author;

    @Before
    public void init(){
        author = authorRepository.save(new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME));
    }

    @Test
    void shouldSaveAndLoadCorrectAuthor() {
        assertThat(author.getFirstName()).isEqualTo(FIELD_INS_FIRSTNAME);
        assertThat(author.getLastName()).isEqualTo(FIELD_INS_LASTNAME);
    }

    @Test
    void shouldDeleteAuthor() {
        authorRepository.delete(author);
        assertThat(authorRepository.findById(author.getId())).isNotPresent();
    }
}