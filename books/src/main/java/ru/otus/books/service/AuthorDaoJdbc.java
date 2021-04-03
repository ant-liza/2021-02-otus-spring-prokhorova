package ru.otus.books.service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.books.dao.AuthorDao;
import ru.otus.books.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return namedParameterJdbcOperations.getJdbcOperations().
                queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("firstName", author.getFirstName());
        params.addValue("lastName", author.getLastName());
        params.addValue("nickName", author.getNickName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into authors (firstName,lastName, nickName) " +
                        " values (:firstName,:lastName,:nickName)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query(
                "select author_id, firstName, lastName, nickName from authors",
                new AuthorMapper());
    }

    @Override
    public Author getById(long authorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", authorId);
        return namedParameterJdbcOperations.queryForObject(
                "select author_id, firstName, lastName, nickName from authors " +
                " where author_id=:id", params, new AuthorMapper());
    }

    @Override
    public void updateFirstNameById(long authorId, String newValue) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", authorId);
        params.addValue("newValue", newValue);
        namedParameterJdbcOperations.update("update authors set firstname = :newValue " +
                "where author_id =:id", params);
    }

    @Override
    public void deleteById(long authorId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", authorId);
        namedParameterJdbcOperations.update("delete from authors where author_id=:id", params);
    }

    @Override
    public long getMaxId() {
        return namedParameterJdbcOperations.getJdbcOperations().
                queryForObject("select max(AUTHOR_ID) from authors", Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("author_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String nickName = resultSet.getString("nickName");
            return new Author(authorId, firstName, lastName, nickName);
        }
    }
}
