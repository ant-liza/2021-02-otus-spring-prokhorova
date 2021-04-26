package ru.otus.books.repositories.jpa;

import ru.otus.books.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepositoryJpa {
    long count();

    Note save(Note note);

    List<Note> findAll();

    Optional<Note> findById(long id);

    void delete(Note note);

    void updateComment(Note note, String comment);
}
