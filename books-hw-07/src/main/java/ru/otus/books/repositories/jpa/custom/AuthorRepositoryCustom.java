package ru.otus.books.repositories.jpa.custom;

import ru.otus.books.exceptions.AuthorNotFoundException;
import ru.otus.books.exceptions.BookNotFoundException;

public interface AuthorRepositoryCustom {
    void deleteAuthorWithBooks(long id) throws BookNotFoundException;

    void addBookToAuthor(long authorId, long bookId)
            throws BookNotFoundException, AuthorNotFoundException;

}
