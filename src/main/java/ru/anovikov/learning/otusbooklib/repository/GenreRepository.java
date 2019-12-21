package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre save(Genre genre);

    void delete(Genre genre);

    Optional<Genre> findById(String id);

    Optional<Genre> findByGenreName(String genreName);

    List<Genre> findAll();

    boolean existsById(String id);

}
