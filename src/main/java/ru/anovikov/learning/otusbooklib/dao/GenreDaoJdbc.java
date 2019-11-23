package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public  GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre insert(Genre genre){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("genreName", genre.getGenreName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into genre (genreName) values (:genreName)", params, kh);
        genre.setId(kh.getKey().longValue());
        return genre;
    }

    @Override
    public void update(Genre genre, long id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("genreName", genre.getGenreName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("update genre set genreName = :genreName where id = :id", params, kh);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genre where id = :id", params
        );
    }

    @Override
    public Genre getById(long id){
        Genre genre = null;
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            genre = namedParameterJdbcOperations.queryForObject(
                    "select id, genreName from genre where id = :id", params, new GenreMapper()
            );
        }
        catch (EmptyResultDataAccessException e) {
            genre = null;
        }

        return genre;
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, genreName from genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String genreName = resultSet.getString("genreName");
            return new Genre(id, genreName);
        }
    }

}
