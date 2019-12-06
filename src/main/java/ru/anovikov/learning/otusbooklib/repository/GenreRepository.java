package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre save(Genre genre);

    void delete(Genre genre);

    Optional<Genre> findById(long id);

    Optional<Genre> findByGenreName(String genreName);

    List<Genre> findAll();

    boolean existsById(long id);

}
