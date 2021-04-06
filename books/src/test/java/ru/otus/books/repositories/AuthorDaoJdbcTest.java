package ru.otus.books.repositories;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.books.domain.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Сервис авторов должен")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class, BookCategoryDaoJdbc.class})
public class AuthorDaoJdbcTest {
    @Autowired
    AuthorDaoJdbc dao;
    public static final int EXPECTED_AUTHORS = 4;

    @DisplayName(" возвращать ожидаемое кол-во авторов")
    @Test
    void shouldReturnExpectedAuthors() {
        int actual = dao.count();
        assertThat(actual).isEqualTo(EXPECTED_AUTHORS);
    }

    @Commit
    @DisplayName(" корректно добавлять нового автора")
    @Test
    void shouldInsertCorrectlyNewAuthor() {
        Author expected = new Author(3, "George", "Martin", null, new ArrayList<>());
        long newAuthorId = dao.insert(expected);
        Author actual = dao.getById(newAuthorId);
        assertThat(actual.getFirstName()).isEqualTo(expected.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(expected.getLastName());
        assertThat(actual.getNickName()).isEqualTo(expected.getNickName());
    }

    @DisplayName("  корректно возвращать ожидаемго автора ")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(3, "George", "Martin", null, new ArrayList<>());
        Author actualBook = dao.getById(expectedAuthor.getAuthorId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName(" кидать DataIntegrityViolationException при удалении автора, " +
            "у которого есть ссылка на существующие в БД книги")
    @Test
    void shouldThrowDataIntegrityViolationExceptionWhenTryDeleteAuthorWithBooks() {
        assertThatCode(() -> dao.getById(1)).doesNotThrowAnyException();
        assertThatCode(()-> dao.deleteById(1))
                .isInstanceOf(DataIntegrityViolationException.class);
         }
    @DisplayName(" при удалении автора с книгами выдавать exception")
    @Test
     void shouldCorrectDeleteAuthorWithoutBooks(){
        Author expected = new Author(3, "George", "Martin", null, new ArrayList<>());
        long newAuthorId = expected.getAuthorId();
        assertThatCode(() -> dao.deleteById(newAuthorId)).doesNotThrowAnyException();

        assertThatCode(()->dao.deleteById(newAuthorId))
                .isInstanceOf(DataIntegrityViolationException.class);
     }

    @DisplayName("  корректно возвращать ожидаемого автора из списка")
    @Test
    void shouldCorrectReturnExpectedBookFromListOfAllBooks() {
        List<Author> expectedAuthorList = Lists.newArrayList();
        expectedAuthorList.add(new Author(1, "Agatha", "Christie", null, new ArrayList<>()));
        expectedAuthorList.add(new Author(2, "Stephen", "King", null, new ArrayList<>()));
        expectedAuthorList.add(new Author(3, "George", "Martin", null, new ArrayList<>()));

        List<Author> actualBookList = dao.getAllAuthors();
        for (Author expectedAuthor : expectedAuthorList) {
            assertThat(actualBookList).contains(expectedAuthor);
        }
    }

}
