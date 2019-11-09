package ru.anovikov.learning.otusbooklib.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
    public void insert(Genre genre){
        HashMap<String, Object> params = new HashMap();
        params.put("id", genre.getId());
        params.put("genreName", genre.getGenreName());
        namedParameterJdbcOperations.update("insert into genres (id, genreName) values (:id, :genreName)", params);
    }

    @Override
    public Genre getById(long id){
        Genre genre = null;
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            genre = namedParameterJdbcOperations.queryForObject(
                    "select id, genreName from genres", params, new GenreMapper()
            );
        }
        catch (EmptyResultDataAccessException e) {
            genre = null;
        }

        return genre;
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, genreName from genres", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genres where id = :id", params
        );
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
