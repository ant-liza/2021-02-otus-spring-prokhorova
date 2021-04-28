package ru.otus.books.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.books.domain.Author;
import ru.otus.books.domain.Book;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.custom.jpa.AuthorRepositoryCustom;

import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void deleteAuthorWithBooks(long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            List<Book> books = author.get().getBooks();
            books.forEach(b -> bookRepository.deleteBookWithRelatedRecords(b.getBookId()));
        }
        authorRepository.deleteById(id);
    }
}
