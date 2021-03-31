package ru.otus.books.service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.books.dao.BookCategoryDao;
import ru.otus.books.domain.BookCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookCategoryDaoJdbc implements BookCategoryDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookCategoryDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations
                .getJdbcOperations()
                .queryForObject("select count(*) from BOOK_CATEGORIES", Integer.class);
    }

    @Override
    public long insert(BookCategory bookCategory) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryName", bookCategory.getBookCategoryName());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(" INSERT INTO BOOK_CATEGORIES (BOOK_CATEGORY_NAME) " +
                        "values (:categoryName)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public List<BookCategory> getAll() {
        return namedParameterJdbcOperations.query(
                "select * from BOOK_CATEGORIES", new BookCategoryMapper());
    }

    @Override
    public BookCategory getById(long bookCategoryId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookCategoryId);
        return namedParameterJdbcOperations.queryForObject(
                "select * from BOOK_CATEGORIES where BOOK_CATEGORY_ID =:id",
                params, new BookCategoryMapper());

    }

    @Override
    public void updateBookCategoryNameById(long bookCategoryId, String value) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryName", value);
        params.addValue("id", bookCategoryId);
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(" UPDATE BOOK_CATEGORIES SET BOOK_CATEGORY_NAME=:categoryName " +
                        " where book_category_id = :id",
                params, kh);
    }

    @Override
    public void deleteById(long bookCategoryId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookCategoryId);
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(" DELETE FROM BOOK_CATEGORIES WHERE BOOK_CATEGORY_ID =:id",
                params, kh);
    }

    private static class BookCategoryMapper implements RowMapper<BookCategory> {
        @Override
        public BookCategory mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookCategoryId = resultSet.getLong("BOOK_CATEGORY_ID");
            String bookCategoryName = resultSet.getString("BOOK_CATEGORY_NAME");
            return new BookCategory(bookCategoryId, bookCategoryName);
        }
    }
}
