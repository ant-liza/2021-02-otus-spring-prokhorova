package ru.otus.books.repositories.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.books.models.Note;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(NoteRepositoryJpaImpl.class)
public class NoteRepositoryJpaTest {
    @Autowired
    TestEntityManager em;
    @Autowired
    NoteRepositoryJpaImpl noteRepositoryJpa;

    private final static long NOTES_COUNT = 4;
    private final static long EXPECTED_NOTE_ID = 1;

    @DisplayName(" корректно возвращать количество комментариев")
    @Test
    void shouldCorrectlyReturnCountOfNotes() {
        long actualCount = noteRepositoryJpa.count();
        assertThat(actualCount).isEqualTo(NOTES_COUNT);
    }

    @DisplayName(" корректно сохранять комментарий")
    @Test
    void shouldCorrectlySaveNote() {
        Note note = new Note(0, "test note");
        noteRepositoryJpa.save(note);
        assertThat(em.find(Note.class, note.getNoteId())).usingRecursiveComparison().isEqualTo(note);
    }

    @DisplayName(" возвращать корректно список комментариев")
    @Test
    void shouldCorrectlyReturnAllNotes() {
        List<Note> actualNotes = noteRepositoryJpa.findAll();
        assertThat(actualNotes).isNotNull()
                .hasSize(4)
                .allMatch(n -> !n.getComment().isEmpty());
    }

    @DisplayName(" находить комментарий по его ИД")
    @Test
    void shouldFindNoteById() {
        Optional<Note> actual = noteRepositoryJpa.findById(EXPECTED_NOTE_ID);
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

        noteRepositoryJpa.delete(noteForDelete);

        assertThat(em.find(Note.class, noteForDelete.getNoteId())).isNull();
    }

    @DisplayName(" изменять комментарий")
    @Test
    void shouldUpdateComment() {
        Note note = new Note(0, "test note");
        em.persist(note);
        em.flush();
        noteRepositoryJpa.updateComment(note, "t t t t");
        assertThat(em.find(Note.class, note.getNoteId()).getComment()).isEqualTo("t t t t");
    }
}
