package ru.otus.books.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.books.domain.Author;
import ru.otus.books.domain.Book;
import ru.otus.books.domain.BookCategory;
import ru.otus.books.domain.Note;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис авторов должен: ")
@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    TestEntityManager tm;
    @Autowired
    AuthorRepository authorRepository;
    private final static long EXPECTED_COUNT = 3;
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
    void shouldDeleteAuthorWithBooks() {
        List<BookCategory> bcList = new ArrayList<>();
        bcList.add(new BookCategory(0, "TestBookCategory"));
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(0,"test note 1 "));
        notes.add(new Note(0,"test note 2 "));
        notes.add(new Note(0,"test note 3 "));

        List<Book> books = new ArrayList<>();
        books.add(new Book(0, bcList, notes, "TestBook"));
        books.add(new Book(0, bcList, notes, "TestBook2"));
        books.add(new Book(0, bcList, notes, "TestBook3"));
        Author author = new Author(0, "test", "test", "test", books);
        tm.persist(author);
        tm.flush();
        System.out.println(" Author before delete = " + tm.find(Author.class, 4L));
        System.out.println("findByTitleContaining = \n" + bookRepository.findByTitleContaining("TestBook"));
        authorRepository.deleteAuthorWithBooks(author.getAuthorId());
        assertThat(tm.find(Book.class, books.get(0).getBookId())).isNull();

        List<Book> actualBooks = bookRepository.findByTitleContaining("TestBook");
        actualBooks.forEach(ab -> assertThat(tm.find(Book.class, ab.getBookId())).isNull());
        List<Note> actualNotes = noteRepository.findByCommentContaining("test");
        actualNotes.forEach(n -> assertThat(tm.find(Note.class, n.getNoteId())).isNull());

        assertThat(tm.find(Author.class, author.getAuthorId())).isNull();
        System.out.println(" Author after delete = " + tm.find(Author.class, 4L));
    }

}
