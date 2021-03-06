package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author save(Author author);

    void delete(Author author);

    Optional<Author> findById(String id);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    List<Author> findAll();

    boolean existsById(String id);

}
