package ru.otus.books.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.books.dao.AuthorDao;
import ru.otus.books.dao.BookDao;
import ru.otus.books.domain.Author;
import ru.otus.books.domain.Book;
import ru.otus.books.repositories.relations.AuthorBookRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final BookDao bookDao;

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
    public List<Author> getAllAuthors() {
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


    @Override
    public List<Author> findAllAuthorsWithBooks() {
        List<Book> books = bookDao.getAllBooks();
        List<AuthorBookRelation> relations = getAllRelations();
        List<Author> authors = getAllAuthors();
        mergeAuthorsAndBooks(authors, books, relations);
        return authors;
    }

    private List<AuthorBookRelation> getAllRelations() {
        return namedParameterJdbcOperations.query(
                "select author_id, book_id from AUTHOR_BOOKS_RELATION",
                (rs, i) -> new AuthorBookRelation(
                        rs.getLong("author_id"),
                        rs.getLong("book_id")));
    }

    private void mergeAuthorsAndBooks(List<Author> authors,
                                      List<Book> books,
                                      List<AuthorBookRelation> relations) {
        Map<Long, Author> authorMap = authors.stream().collect(Collectors.toMap(Author::getAuthorId, a -> a));
        Map<Long, Book> booksMap = books.stream().collect(Collectors.toMap(Book::getBookId, b -> b));
        relations.stream().forEach(rel -> {
            if (authorMap.containsKey(rel.getAuthorId()) &&
                    booksMap.containsKey(rel.getBookId())) {
                authorMap.get(rel.getAuthorId()).getBooks().add(booksMap.get(rel.getBookId()));
            }
        });
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("author_id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String nickName = resultSet.getString("nickName");
            return new Author(authorId, firstName, lastName, nickName, new ArrayList<>());
        }
    }
}
