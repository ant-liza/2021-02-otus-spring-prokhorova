package ru.otus.books.repositories.jpa.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.AuthorNotFoundException;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Author;
import ru.otus.books.models.Book;
import ru.otus.books.repositories.jpa.custom.AuthorRepositoryCustom;
import ru.otus.books.repositories.jpa.AuthorRepository;
import ru.otus.books.repositories.jpa.BookRepository;

import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional(rollbackFor = BookNotFoundException.class)
    public void deleteAuthorWithBooks(long id) throws BookNotFoundException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            List<Book> books = author.get().getBooks();
            for (Book b : books) {
                bookRepository.deleteBookWithRelatedRecords(b.getBookId());
            }
        }
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = {BookNotFoundException.class, AuthorNotFoundException.class})
    public void addBookToAuthor(long authorId, long bookId)
            throws BookNotFoundException, AuthorNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Author> author = authorRepository.findById(authorId);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Книга не найдена");
        } else if (author.isEmpty()) {
            throw new AuthorNotFoundException("Автор не найден");
        } else {
            List<Book> authorBooks = author.get().getBooks();
            authorBooks.add(book.get());
            author.get().setBooks(authorBooks);
            authorRepository.save(author.get());
        }
    }

}