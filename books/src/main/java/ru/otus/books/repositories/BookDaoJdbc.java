package ru.otus.books.repositories;

import com.google.common.collect.Lists;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.books.dao.BookCategoryDao;
import ru.otus.books.dao.BookDao;
import ru.otus.books.domain.Author;
import ru.otus.books.domain.Book;
import ru.otus.books.domain.BookCategory;
import ru.otus.books.repositories.relations.BookCategoryToBookRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final BookCategoryDao bookCategoryDao;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       BookCategoryDao bookCategoryDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookCategoryDao = bookCategoryDao;
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
        params.addValue("author", book.getAuthor().getAuthorId());
        params.addValue("desc", book.getTitle());
        params.addValue("bookId", book.getBookId());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into books (AUTHOR_ID,TITLE)" +
                        " values (:author,:desc)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookCategory> bookCategories = bookCategoryDao.getAll();
        List<BookCategoryToBookRelation> relations = getBookToCategoryRelation();
        List<Book> books = namedParameterJdbcOperations.query(
                "select books.book_id, TITLE," +
                        " AUTHORS.author_id author_id,firstname,lastname,nickname" +
                        "  from books " +
                        " inner join AUTHORS on books.author_id = AUTHORS.author_id",
                new BookMapper());

        mergeBookAndCategories(bookCategories,books, relations);
        return books;
    }
    private void mergeBookAndCategories(List<BookCategory> bookCategories,
                                      List<Book> books,
                                      List<BookCategoryToBookRelation> relations) {
        Map<Long, BookCategory> bookCategoryMap =
                bookCategories.stream().collect(Collectors.toMap(BookCategory::getBookCategoryId, bc -> bc));
        Map<Long, Book> booksMap = books.stream().collect(Collectors.toMap(Book::getBookId, b -> b));
        relations.stream().forEach(rel -> {
            if(booksMap.containsKey(rel.getBookId()) &&
                    bookCategoryMap.containsKey(rel.getBookCategoryId())){
                Book b = booksMap.get(rel.getBookId());
                BookCategory bc = bookCategoryMap.get(rel.getBookCategoryId());
                b.getBookCategory().add(bc);
            }
        });
    }

    @Override
    public Book getById(long bookId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        Book book = namedParameterJdbcOperations.queryForObject(
                "select books.book_id, TITLE," +
                        " AUTHORS.author_id author_id,firstname,lastname,nickname" +
                        "  from books " +
                        " inner join AUTHORS on books.author_id = AUTHORS.author_id " +
                        " where book_id=:id",
                params, new BookMapper());
        List<BookCategory> bookCategories = bookCategoryDao.getAll();
        List<BookCategoryToBookRelation> relations = getBookToCategoryRelation();
        mergeBookAndCategories(bookCategories, Lists.newArrayList(book), relations);
        return book;
    }

    @Override
    public void updateBookDescriptionById(long bookId, String newValue) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        params.addValue("desc", newValue);
        namedParameterJdbcOperations.update("update books set title =:desc " +
                "where book_id=:id", params);
    }

    @Override
    public void deleteById(long bookId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookId);
        namedParameterJdbcOperations.update("delete from books where book_id=:id", params);
        namedParameterJdbcOperations.update("delete from BOOK_CATEGORY_RELATION where book_id=:id", params);
        namedParameterJdbcOperations.update("delete from AUTHOR_BOOKS_RELATION where book_id=:id", params);

    }

    private List<BookCategoryToBookRelation> getBookToCategoryRelation() {
        return namedParameterJdbcOperations.query(
                "select book_id, book_category_id from BOOK_CATEGORY_RELATION",
                (rs, i) -> new BookCategoryToBookRelation(
                        rs.getLong("book_id"),
                        rs.getLong("book_category_id")));
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long bookId = rs.getLong("book_ID");
            String bookDescription = rs.getString("TITLE");
            Author author = new Author(
                    rs.getLong("author_id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("nickname"),
                    new ArrayList<>());
            return new Book(bookId, author, new ArrayList<>(), bookDescription);
        }
    }
}
