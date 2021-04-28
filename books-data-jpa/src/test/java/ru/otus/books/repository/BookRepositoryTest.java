package ru.otus.books.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.books.domain.Book;
import ru.otus.books.domain.BookCategory;
import ru.otus.books.domain.Note;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис книг должен:")
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TestEntityManager em;

    @DisplayName("при удалении книги удалять комментарии")
    @Test
    void shouldDeleteBookWithItsNotes() {
        List<BookCategory> bcList = new ArrayList<>();
        bcList.add(new BookCategory(0, "TestBookCategory"));
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(0, "test note 1 "));
        notes.add(new Note(0, "test note 2 "));
        notes.add(new Note(0, "test note 3 "));

        Book book = new Book(0, bcList, notes, "TEST");
        em.persist(book);
        em.flush();

        System.out.println(" book = " + book);
        System.out.println(" note 5 = " + em.find(Note.class, 5L));
        bookRepository.deleteBookWithRelatedRecords(book.getBookId());

        notes.forEach(actualN-> assertThat(em.find(Note.class, actualN.getNoteId())).isNull());

        assertThat(em.find(Book.class, book.getBookId())).isNull();
    }
}
