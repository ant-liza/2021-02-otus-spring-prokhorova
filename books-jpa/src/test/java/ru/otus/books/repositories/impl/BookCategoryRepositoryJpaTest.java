package ru.otus.books.repositories.impl;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.books.models.BookCategory;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис категорий книг должен:")
@DataJpaTest
@Import(BookCategoryRepositoryJpaImpl.class)
public class BookCategoryRepositoryJpaTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    BookCategoryRepositoryJpaImpl jpa;

    private final static int BC_COUNT_EXPECTED_AFTER_ADDING_NEW = 3;
    private final static int BC_COUNT_EXPECTED_BEFORE_ADDING_NEW = 2;
    private final static long BC_ID = 1;

    @DisplayName(" должен возвращать корректное число категорий")
    @Test
    void shouldReturnCorrectCountOfCategory() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        int actualCount = (int) jpa.count();
        System.out.println(sessionFactory.getStatistics().getPrepareStatementCount());
        assertThat(actualCount).isEqualTo(BC_COUNT_EXPECTED_BEFORE_ADDING_NEW);
    }


    @DisplayName(" корректно добавлять новую категорию")
    @Test
    void shouldCorrectlyAddNewBookCategory() {
        BookCategory bc = new BookCategory(0, "Русская классика");
        jpa.save(bc);
        assertThat(em.find(BookCategory.class, bc.getBookCategoryId()))
                .usingRecursiveComparison().isEqualTo(bc);
        assertThat(jpa.findAll()).hasSize(BC_COUNT_EXPECTED_AFTER_ADDING_NEW);
    }

    @DisplayName(" корректно удалять категорию")
    @Test
    void shouldCorrectlyDeleteBookCategory() {
        BookCategory bc = new BookCategory(0, "Русская классика");
        em.persist(bc);
        em.flush();
        assertThat(jpa.findAll()).hasSize(BC_COUNT_EXPECTED_AFTER_ADDING_NEW);
        jpa.delete(bc);
        assertThat(em.find(BookCategory.class, bc.getBookCategoryId())).isNull();
        assertThat(jpa.findAll()).hasSize(2);
    }

    @DisplayName(" должен обновлять корректно категорию")
    @Test
    void shouldUpdateBookCategoryNameById() {
        BookCategory actualBC = em.find(BookCategory.class,BC_ID);
        actualBC.setBookCategoryName("Фэнтези");
        jpa.save(actualBC);
        assertThat(em.find(BookCategory.class,BC_ID).getBookCategoryName()).isEqualTo("Фэнтези");
    }
}
