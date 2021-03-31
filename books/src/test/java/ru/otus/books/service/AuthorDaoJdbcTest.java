package ru.otus.books.service;

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
import ru.otus.books.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Сервис авторов должен")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {
    @Autowired
    AuthorDaoJdbc dao;
    public static final int EXPECTED_AUTHORS = 3;

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
        Author expected = new Author(3, "George", "Martin", null);
        long newAuthorId = dao.insert(expected);
        Author actual = dao.getById(newAuthorId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("  корректно возвращать ожидаемго автора ")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(3, "George", "Martin", null);
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
    @DisplayName(" корректно удалять автора без книг")
    @Test
     void shouldCorrectDeleteAuthorWithoutBooks(){
        Author expected = new Author(3, "George", "Martin", null);
        long newAuthorId = expected.getAuthorId();
        assertThatCode(() -> dao.deleteById(newAuthorId)).doesNotThrowAnyException();

        dao.deleteById(newAuthorId);

        assertThatCode(()-> dao.getById(newAuthorId))
                .isInstanceOf(EmptyResultDataAccessException.class);
     }

    @DisplayName("  корректно возвращать ожидаемого автора из списка")
    @Test
    void shouldCorrectReturnExpectedBookFromListOfAllBooks() {
        List<Author> expectedAuthorList = Lists.newArrayList();
        expectedAuthorList.add(new Author(1, "Agatha", "Christie", null));
        expectedAuthorList.add(new Author(2, "Stephen", "King", null));
        expectedAuthorList.add(new Author(3, "George", "Martin", null));

        List<Author> actualBookList = dao.getAll();
        for (Author expectedAuthor : expectedAuthorList) {
            assertThat(actualBookList).contains(expectedAuthor);
        }
    }

}
