package ru.otus.books.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.books.models.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис книг должен")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
public class BookRepositoryJpaTest {

    private final static long EXPECTED_COUNT = 8;
    @Autowired
    TestEntityManager em;
    @Autowired
    BookRepositoryJpaImpl bookJpa;

    @Test
    void shouldReturnCount(){
        long actualCount = bookJpa.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    void shouldDeleteBook(){
        Book book = new Book(0, null,null, "TestBook");
        em.persist(book);
        em.flush();
        bookJpa.delete(book);
        assertThat(em.find(Book.class, book.getBookId())).isNull();
    }

    @Test
    void shouldUpdateBookTitle(){
        Book book = new Book(0, null,null, "TestBook");
        em.persist(book);
        em.flush();
        bookJpa.updateTitle(book, "BokBok");
        assertThat(em.find(Book.class, book.getBookId()).getTitle()).isEqualTo("BokBok");
    }

}
