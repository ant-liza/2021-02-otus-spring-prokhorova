package ru.otus.books.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.custom.AuthorRepositoryCustom;
import ru.otus.books.repository.custom.SequenceRepositoryCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @Autowired
    SequenceRepositoryCustom sequenceRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    public void insertAuthorWithGeneratedId(Author author) {
        author.setAuthorId(sequenceRepository.getNextSequenceId(Author.COLLECTION_NAME));
        authorRepository.save(author);
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (bookOptional.isPresent() && authorOptional.isPresent()) {
            final Author author = authorOptional.get();
            List<Book> authorBooks = author.getBooks();
            if (authorBooks == null) {
                authorBooks = new ArrayList<>();
            }
            authorBooks.add(bookOptional.get());
            author.setBooks(authorBooks);
            authorRepository.save(author);
        }
    }
}
