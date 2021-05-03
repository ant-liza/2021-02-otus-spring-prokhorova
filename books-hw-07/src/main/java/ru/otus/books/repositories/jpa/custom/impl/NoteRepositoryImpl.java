package ru.otus.books.repositories.jpa.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Book;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookRepository;
import ru.otus.books.repositories.jpa.custom.NoteRepositoryCustom;

import java.util.List;
import java.util.Optional;

public class NoteRepositoryImpl implements NoteRepositoryCustom {
    @Autowired
    BookRepository bookRepository;

    @Transactional
    @Override
    public void addNoteToBook(Note note, long bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            final Book existsBook = book.get();
            List<Note> notes = existsBook.getNotes();
            notes.add(note);
            existsBook.setNotes(notes);
            bookRepository.save(existsBook);
        } else {
            throw new BookNotFoundException("Книга не найдена");
        }
    }
}
