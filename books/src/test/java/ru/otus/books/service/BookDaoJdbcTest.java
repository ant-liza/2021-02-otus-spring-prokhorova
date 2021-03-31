package ru.otus.books.service;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.books.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Сервис книг должен")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {
    @Autowired
    private BookDaoJdbc dao;
    public static final int EXPECTED_COUNT = 8;
    public static final int EXISTING_BOOK_ID = 8;
    public static final int EXISTING_AUTHOR_ID = 2;
    public static final int EXISTING_BOOK_CATEGORY_ID = 1;
    public static final String EXISTING_BOOK_DESCRIPTION = "Misery";

    @DisplayName("  вернуть ожидаемое кол-во книг ")
    @Test
    void shouldReturnExpectedBookCount() {
        int actual = dao.count();
        Assertions.assertEquals(actual, EXPECTED_COUNT);
    }

    @Commit
    @DisplayName("  корректно добавлять новую книгу ")
    @Test
    void shouldInsertCorrectlyNewBook() {
        Book newBook = new Book(EXISTING_BOOK_ID, EXISTING_AUTHOR_ID,
                EXISTING_BOOK_CATEGORY_ID, EXISTING_BOOK_DESCRIPTION);
        long idAfterInsert = dao.insert(newBook);
        Book insertedBook = dao.getById(idAfterInsert);
        assertThat(insertedBook).usingRecursiveComparison().isEqualTo(newBook);
    }

    @DisplayName("  корректно возвращать ожидаемую книгу ")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_AUTHOR_ID,
                EXISTING_BOOK_CATEGORY_ID, EXISTING_BOOK_DESCRIPTION);
        Book actualBook = dao.getById(expectedBook.getBookId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("  корректно удалять заданную книгу по ее ИД")
    @Test
    void shouldCorrectlyDeleteBookByBookId() {
        assertThatCode(() -> dao.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        dao.deleteById(EXISTING_BOOK_ID);
        assertThatCode(() -> dao.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("  корректно возвращать ожидаемую книгу из списка")
    @Test
    void shouldCorrectReturnExpectedBookFromListOfAllBooks() {
        List<Book> expectedBookList = Lists.newArrayList();
        expectedBookList.add(new Book(1,1,2,"Десять негритят"));
        expectedBookList.add(new Book(2,1,2,"Вилла Белый Конь"));
        expectedBookList.add(new Book(3,2,1,"Стрелок"));
        expectedBookList.add(new Book(4,2,1,"Извлечение троих"));
        expectedBookList.add(new Book(5,2,2,"Мистер Мерседес"));
        expectedBookList.add(new Book(6,2,2,"Кто нашел, берет себе"));
        expectedBookList.add(new Book(7,2,2,"Пост сдал"));
        expectedBookList.add(new Book(EXISTING_BOOK_ID, EXISTING_AUTHOR_ID,
                EXISTING_BOOK_CATEGORY_ID, EXISTING_BOOK_DESCRIPTION));
        List<Book> actualBookList = dao.getAll();
        for (Book expectedBook : expectedBookList) {
            assertThat(actualBookList).contains(expectedBook);
        }
    }
}
