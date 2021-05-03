package ru.otus.books.repositories.jpa.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.BookCategoryNotFoundException;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Book;
import ru.otus.books.models.BookCategory;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookCategoryRepository;
import ru.otus.books.repositories.jpa.custom.BookRepositoryCustom;
import ru.otus.books.repositories.jpa.BookRepository;
import ru.otus.books.repositories.jpa.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepositoryCustom {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Override
    @Transactional(rollbackFor = BookNotFoundException.class)
    public void deleteBookWithRelatedRecords(long bookId) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            List<Note> notes = book.get().getNotes();
            if (!notes.isEmpty()) {
                noteRepository.deleteAll(notes);
            }
            bookRepository.delete(book.get());
        } else {
            throw new BookNotFoundException("Книга не найдена");
        }
    }

    @Override
    public void addBook(String title, long bookCategoryId) throws BookCategoryNotFoundException {
        Optional<BookCategory> bc = bookCategoryRepository.findById(bookCategoryId);
        if (bc.isEmpty()) {
            throw new BookCategoryNotFoundException("Жанр с ид = " + bookCategoryId + " не найден");
        }
        List<BookCategory> bcList = new ArrayList<>();
        bcList.add(bc.get());
        Book book = new Book(0, bcList, null, title);
        bookRepository.saveAndFlush(book);
    }
}
