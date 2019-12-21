package ru.anovikov.learning.otusbooklib.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.repository.AuthorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthorServiceImplTest {

    private static final String FIELD_INS_ID = "";
    private static final String FIELD_INS_FIRSTNAME = "firstname";
    private static final String FIELD_INS_LASTNAME = "lastname";

    @InjectMocks
    AuthorServiceImpl authorService;

    @Mock
    AuthorRepository authorRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCheckDuplicateInsertAuthor() {
        Author author = new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        when(authorRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(author));

        assertThrows(DuplicateValueException.class, () -> {
            authorService.insert(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        });
    }

    @Test
    void shouldCheckDuplicateUpdateAuthor() {
        Author author = new Author(FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        when(authorRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(author));
        when(authorRepository.existsById(anyString())).thenReturn(true);

        assertThrows(DuplicateValueException.class, () -> {
            authorService.update(FIELD_INS_ID, FIELD_INS_FIRSTNAME, FIELD_INS_LASTNAME);
        });
    }
}