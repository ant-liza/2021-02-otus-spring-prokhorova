package ru.otus.books.repository.custom.jpa;

public interface AuthorRepositoryCustom {
    void deleteAuthorWithBooks(long id);
}
