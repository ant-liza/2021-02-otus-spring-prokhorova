package ru.otus.books.repositories.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Book;
import ru.otus.books.models.Note;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NoteRepositoryTest {
    @Autowired
    TestEntityManager em;
    @Autowired
    NoteRepository noteRepository;

    private final static long NOTES_COUNT = 4;
    private final static long EXPECTED_NOTE_ID = 1;
    private final static long EXPECTED_BOOK_ID = 1;
    private final static int EXPECTED_SIZE_OF_BOOK_NOTES = 3;
    private final static String TEST_COMMENT = "Test NOTE comment";

    @DisplayName(" корректно возвращать количество комментариев")
    @Test
    void shouldCorrectlyReturnCountOfNotes() {
        long actualCount = noteRepository.count();
        assertThat(actualCount).isEqualTo(NOTES_COUNT);
    }

    @DisplayName(" корректно сохранять комментарий")
    @Test
    void shouldCorrectlySaveNote() {
        Note note = new Note(0, "test note");
        noteRepository.save(note);
        assertThat(em.find(Note.class, note.getNoteId())).usingRecursiveComparison().isEqualTo(note);
    }

    @DisplayName(" возвращать корректно список комментариев")
    @Test
    void shouldCorrectlyReturnAllNotes() {
        List<Note> actualNotes = noteRepository.findAll();
        assertThat(actualNotes).isNotNull()
                .hasSize(4)
                .allMatch(n -> !n.getComment().isEmpty());
    }

    @DisplayName(" находить комментарий по его ИД")
    @Test
    void shouldFindNoteById() {
        Optional<Note> actual = noteRepository.findById(EXPECTED_NOTE_ID);
        Note expected = em.find(Note.class, EXPECTED_NOTE_ID);
        assertThat(actual).isPresent().get().usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @DisplayName(" удалять комментарий")
    @Test
    void shouldCorrectlyDeleteNote() {
        Note noteForDelete = new Note(0, "test note");
        em.persist(noteForDelete);
        em.flush();

        noteRepository.delete(noteForDelete);

        assertThat(em.find(Note.class, noteForDelete.getNoteId())).isNull();
    }

    @DisplayName(" возвращать список комментариев для книги")
    @Test
    void shouldReturnNotesForBook() {
        Book book = em.find(Book.class, EXPECTED_BOOK_ID);
        assertThat(book.getNotes())
                .isNotNull()
                .hasSize(EXPECTED_SIZE_OF_BOOK_NOTES)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(book.getNotes());
    }
    @DisplayName(" добавлять комментарий к книге")
    @Test
    void shouldAddNoteToExistsBook() throws BookNotFoundException {
        Note note = new Note(0, TEST_COMMENT);
        noteRepository.addNoteToBook(note, EXPECTED_BOOK_ID);
        Book book = em.find(Book.class, EXPECTED_BOOK_ID);
        assertThat(book.getNotes())
                .hasSize(4)
                .anyMatch(n -> n.getComment().equalsIgnoreCase(TEST_COMMENT));
    }
}
