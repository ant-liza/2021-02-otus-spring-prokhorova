package ru.otus.books.repositories.jpa.custom;

import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Note;

public interface NoteRepositoryCustom {
    void addNoteToBook(Note note, long bookId) throws BookNotFoundException;
}
