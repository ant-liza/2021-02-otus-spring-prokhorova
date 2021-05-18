package ru.otus.books.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.books.model.Book;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.custom.BookRepositoryCustom;
import ru.otus.books.repository.custom.SequenceRepositoryCustom;

public class BookRepositoryImpl implements BookRepositoryCustom {
    @Autowired
    SequenceRepositoryCustom sequenceRepository;
    @Autowired
    BookRepository bookRepository;


    @Override
    public void insertBookWithGeneratedId(Book book) {
        book.setBookId(sequenceRepository.getNextSequenceId(Book.COLLECTION_NAME));
        bookRepository.save(book);
    }
}
