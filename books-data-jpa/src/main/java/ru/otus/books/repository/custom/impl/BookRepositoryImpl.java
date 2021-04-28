package ru.otus.books.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.books.domain.Book;
import ru.otus.books.domain.Note;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.NoteRepository;
import ru.otus.books.repository.custom.jpa.BookRepositoryCustom;

import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepositoryCustom {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    NoteRepository noteRepository;

    @Override
    public void deleteBookWithRelatedRecords(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Note> notes = book.get().getNotes();
            if (!notes.isEmpty()) {
                noteRepository.deleteAll(notes);
            }
            bookRepository.delete(book.get());
        }
    }
}
