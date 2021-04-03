package ru.otus.books.service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.books.dao.BookDao;
import ru.otus.books.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations
                .getJdbcOperations()
                .queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", book.getAuthorId());
        params.addValue("bookCat", book.getBookCategoryId());
        params.addValue("desc", book.getDescription());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION)" +
                        " values (:author,:bookCat,:desc)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select BOOK_ID, AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION" +
                        " from books", new BookMapper());
    }

    @Override
    public Book getById(long bookId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        return namedParameterJdbcOperations.queryForObject(
                "select BOOK_ID, AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION" +
                        " from books where book_id=:id",
                params, new BookMapper());
    }

    @Override
    public void updateBookDescriptionById(long bookId, String newValue) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        params.addValue("desc", newValue);
        namedParameterJdbcOperations.update("update books set description =:desc " +
                "where book_id=:id", params);
    }

    @Override
    public void deleteById(long bookId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        namedParameterJdbcOperations.update("delete from books where book_id=:id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("BOOK_ID");
            int authorId = rs.getInt("AUTHOR_ID");
            int bookCategoryId = rs.getInt("BOOK_CATEGORY_ID");
            String bookDescription = rs.getString("DESCRIPTION");
            return new Book(bookId, authorId, bookCategoryId, bookDescription);
        }
    }
}
