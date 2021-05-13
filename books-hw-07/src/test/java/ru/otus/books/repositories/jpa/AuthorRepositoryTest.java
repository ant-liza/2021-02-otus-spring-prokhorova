package ru.otus.books.repositories.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Author;
import ru.otus.books.models.Book;
import ru.otus.books.models.BookCategory;
import ru.otus.books.models.Note;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис авторов должен: ")
@DataJpaTest
public class AuthorRepositoryTest {
    private final static long EXPECTED_COUNT = 3;
    private final static long BOOK_CATEGORY = 3;
    @Autowired
    TestEntityManager em;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    NoteRepository noteRepository;

    @DisplayName(" корректно считать количество авторов")
    @Test
    void shouldReturnCountOfAuthors() {
        long actualCount = authorRepository.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName(" при удалении автора удалять все его книги и связанные записи")
    @Test
    void shouldDeleteAuthorWithBooks() throws BookNotFoundException {
        List<BookCategory> bcList = new ArrayList<>();
        bcList.add(em.find(BookCategory.class,BOOK_CATEGORY));

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(0, "test note 1 "));
        notes.add(new Note(0, "test note 2 "));
        notes.add(new Note(0, "test note 3 "));
        noteRepository.saveAll(notes);

        List<Book> books = new ArrayList<>();
        books.add(new Book(0, bcList, notes, "TestBook"));
        books.add(new Book(0, bcList, notes, "TestBook2"));
        books.add(new Book(0, bcList, notes, "TestBook3"));
        bookRepository.saveAll(books);

        Author author = new Author(0, "test", "test", "test", books);
        em.persist(author);
        em.flush();
        System.out.println(" Author before delete = " + em.find(Author.class, 4L));
        System.out.println("findByTitleContaining = \n" + bookRepository.findByTitleContaining("TestBook"));
        authorRepository.deleteAuthorWithBooks(author.getAuthorId());
        assertThat(em.find(Book.class, books.get(0).getBookId())).isNull();

        List<Book> actualBooks = bookRepository.findByTitleContaining("TestBook");
        actualBooks.forEach(ab -> assertThat(em.find(Book.class, ab.getBookId())).isNull());
        List<Note> actualNotes = noteRepository.findByCommentContaining("test");
        actualNotes.forEach(n -> assertThat(em.find(Note.class, n.getNoteId())).isNull());

        assertThat(em.find(Author.class, author.getAuthorId())).isNull();
        System.out.println(" Author after delete = " + em.find(Author.class, 4L));
    }
}
