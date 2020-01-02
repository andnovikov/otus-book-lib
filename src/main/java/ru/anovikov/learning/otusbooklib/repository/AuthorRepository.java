package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author save(Author author);

    void delete(Author author);

    Optional<Author> findById(long id);

    Optional<Author> findByName(String firstName, String lastName);

    List<Author> findAll();

    boolean existsById(long id);

}
