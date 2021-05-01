package ru.otus.books.repositories.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.books.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис книг должен")
@DataJpaTest
public class BookRepositoryJpaTest {

    private final static int EXPECTED_COUNT = 8;
    private final static long EXPECTED_BOOK_ID = 1;
    private final static long EXPECTED_AUTHOR_ID = 1;
    private final static int EXPECTED_BOOK_AUTHOR_COUNT = 2;
    @Autowired
    TestEntityManager em;
    @Autowired
    BookRepositoryJpa bookJpa;

    @DisplayName(" должен возвращать список книг у автора")
    @Test
    void shouldReturnAllBooksByAuthor() {
        Author author = em.find(Author.class, EXPECTED_AUTHOR_ID);
        assertThat(bookJpa.getAllBooksByAuthor(EXPECTED_AUTHOR_ID))
                .isNotNull()
                .hasSize(EXPECTED_BOOK_AUTHOR_COUNT)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(author.getBooks());
    }

}
