package ru.otus.books.repositories.impl;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.books.models.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис Авторов ")
@DataJpaTest
@Import(AuthorRepositoryJpaImpl.class)
public class AuthorRepositoryJpaTest {
    private final static int AUTHORS_COUNT_EXPECTED = 3;
    private final static int QUERIES_COUNT_EXPECTED = 4;
    private final static long AUTHORS_ID_EXPECTED = 3;
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepositoryJpaImpl authorRepositoryJpa;

    @DisplayName(" должен возвращать правильное количество  авторов")
    @Test
    void shouldCorrectlyReturnCountOfAuthors() {
        int actualAuthorsCount = (int) authorRepositoryJpa.count();
        assertThat(actualAuthorsCount).isEqualTo(AUTHORS_COUNT_EXPECTED);
    }

    @DisplayName(" должен возвращать всех авторов")
    @Test
    void shouldReturnAllAuthors() {
        List<Author> actualList = authorRepositoryJpa.findAll();
        assertThat(actualList).hasSize(AUTHORS_COUNT_EXPECTED);
    }


    @DisplayName(" должен загружать список всех авторов с книгами")
    @Test
    void shouldReturnCorrectAuthorListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        List<Author> authors = authorRepositoryJpa.findAll();
        System.out.println("count of queries = " + sessionFactory.getStatistics().getPrepareStatementCount());
        assertThat(authors)
                .isNotNull()
                .hasSize(AUTHORS_COUNT_EXPECTED)
                .allMatch(a -> !a.getFirstName().equals(""))
                .allMatch(a -> !a.getLastName().equals(""))
                .allMatch(a -> a.getBooks() != null && a.getBooks().size() > 0);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(QUERIES_COUNT_EXPECTED);
    }

    @DisplayName(" должен найти автора по ИД")
    @Test
    void shouldFindAuthorById() {
        Optional<Author> actual = authorRepositoryJpa.findById(AUTHORS_ID_EXPECTED);
        System.out.println("actual = " + actual);
        Author expected = em.find(Author.class, AUTHORS_ID_EXPECTED);
        System.out.println("expected = " + expected);
        assertThat(actual)
                .isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName(" должен сохранять нового автора")
    @Test
    void shouldSaveNewAuthor() {
        Author expected = new Author(0, "test", "test", null, null);
        assertThat(em.find(Author.class, expected.getAuthorId())).isNull();
        authorRepositoryJpa.save(expected);
        System.out.println(" expected = " + expected);
        Author actual = em.find(Author.class, expected.getAuthorId());
        System.out.println(" actual = " + actual);
        assertThat(expected).usingRecursiveComparison().isEqualTo(actual);
    }

    @DisplayName(" должен удалять автора")
    @Test
    void shouldDeleteAuthorById() {
        Author expectedForDelete = new Author(0, "test", "test", null, null);
        em.persist(expectedForDelete);
        em.flush();
        assertThat(em.find(Author.class, expectedForDelete.getAuthorId())).isNotNull();

        authorRepositoryJpa.delete(expectedForDelete.getAuthorId());
        assertThat(em.find(Author.class, expectedForDelete.getAuthorId())).isNull();
    }

    @DisplayName(" должен изменять имя автора на новое")
    @Test
    void shouldUpdateFirstName() {
        Author newAuthor = new Author(0, "test", "test", null, null);
        em.persist(newAuthor);
        em.flush();
        System.out.println(" old fn = " + newAuthor.getFirstName());
        newAuthor.setFirstName("Kelly");
        authorRepositoryJpa.save(newAuthor);
        System.out.println(" new fn = " + newAuthor.getFirstName());
        assertThat(em.find(Author.class, newAuthor.getAuthorId())
                .getFirstName()).isEqualTo("Kelly");
    }
}
