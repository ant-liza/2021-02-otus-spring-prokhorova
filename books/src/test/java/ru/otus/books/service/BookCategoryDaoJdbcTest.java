package ru.otus.books.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import ru.otus.books.domain.BookCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName(" Сервис жанров книг должен")
@JdbcTest
@Import(BookCategoryDaoJdbc.class)
public class BookCategoryDaoJdbcTest {
    @Autowired
    BookCategoryDaoJdbc dao;
    public static final int EXPECTED_CATEGORIES = 3;

    @DisplayName(" возвращать ожидаемое кол-во авторов")
    @Test
    void shouldReturnExpectedAuthors() {
        int actual = dao.count();
        assertThat(actual).isEqualTo(EXPECTED_CATEGORIES);
    }

    @Commit
    @DisplayName(" корректно добавлять новый жанр ")
    @Test
    void shouldInsertCorrectlyBookCategory() {
        BookCategory expected = new BookCategory(3, "Русская классика");
        long newCategoryId = dao.insert(expected);
        BookCategory actual = dao.getById(newCategoryId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("  корректно возвращать ожидаемый жанр ")
    @Test
    void shouldReturnExpectedAuthorById() {
        BookCategory expectedBookCategory = new BookCategory(3, "Русская классика");
        BookCategory actualBookCategory = dao.getById(expectedBookCategory.getBookCategoryId());
        assertThat(actualBookCategory).usingRecursiveComparison().isEqualTo(expectedBookCategory);
    }

    @DisplayName(" кидать DataIntegrityViolationException при удалении жанра, " +
            "у которого есть ссылка на существующие в БД книги")
    @Test
    void shouldThrowDataIntegrityViolationExceptionWhenTryDeleteAuthorWithBooks() {
        assertThatCode(() -> dao.getById(1)).doesNotThrowAnyException();
        assertThatCode(()-> dao.deleteById(1))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
    @DisplayName(" корректно удалять жанр без книг")
    @Test
    void shouldCorrectDeleteAuthorWithoutBooks(){
        BookCategory expected = new BookCategory(3, "Русская классика");
        long newBookCatId = expected.getBookCategoryId();
        assertThatCode(() -> dao.deleteById(newBookCatId)).doesNotThrowAnyException();

        dao.deleteById(newBookCatId);

        assertThatCode(()-> dao.getById(newBookCatId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("  корректно возвращать ожидаемый жанр из списка")
    @Test
    void shouldCorrectReturnExpectedBookFromListOfAllBooks() {
        List<BookCategory> expectedCategoryList = Lists.newArrayList();
        expectedCategoryList.add(new BookCategory(1, "Фантастика"));
        expectedCategoryList.add(new BookCategory(2, "Детектив"));

        List<BookCategory> actualCategoryList = dao.getAll();
        for (BookCategory expectedCategory : expectedCategoryList) {
            assertThat(actualCategoryList).contains(expectedCategory);
        }
    }

}
