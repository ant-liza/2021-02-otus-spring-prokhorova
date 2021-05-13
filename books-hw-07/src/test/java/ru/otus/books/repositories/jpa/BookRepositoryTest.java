package ru.otus.books.repositories.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Author;
import ru.otus.books.models.Book;
import ru.otus.books.models.BookCategory;
import ru.otus.books.models.Note;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис книг должен")
@DataJpaTest
public class BookRepositoryTest {

    private final static long EXPECTED_AUTHOR_ID = 1;
    private final static int EXPECTED_BOOK_AUTHOR_COUNT = 2;
    private final static long BOOK_CATEGORY = 3;
    @Autowired
    TestEntityManager em;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    NoteRepository noteRepository;

    @DisplayName(" должен возвращать список книг у автора")
    @Test
    void shouldReturnAllBooksByAuthor() {
        Author author = em.find(Author.class, EXPECTED_AUTHOR_ID);
        assertThat(author.getBooks())
                .isNotNull()
                .hasSize(EXPECTED_BOOK_AUTHOR_COUNT)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(author.getBooks());
    }

    @DisplayName(" должен удалять книгу и связанные комментарии")
    @Test
    void shouldDeleteBookWithItsNotes() throws BookNotFoundException {
        List<BookCategory> bcList = new ArrayList<>();
        bcList.add(em.find(BookCategory.class,BOOK_CATEGORY));

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(0, "test note 1 "));
        notes.add(new Note(0, "test note 2 "));
        notes.add(new Note(0, "test note 3 "));
        noteRepository.saveAll(notes);

        Book book = new Book(0, bcList, notes, "TEST");
        em.persist(book);
        em.flush();

        System.out.println(" book = " + book);
        System.out.println(" note 5 = " + em.find(Note.class, 5L));
        bookRepository.deleteBookWithRelatedRecords(book.getBookId());

        notes.forEach(actualN -> assertThat(em.find(Note.class, actualN.getNoteId())).isNull());

        assertThat(em.find(Book.class, book.getBookId())).isNull();
    }

}
