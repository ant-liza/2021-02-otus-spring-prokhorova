package ru.otus.books.repositories.jpa;

import ru.otus.books.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {
    long count();

    Author save(Author author);

    List<Author> findAll();

    Optional<Author> findById(long id);

    void delete(long id);

    void updateFirstName(Author author,String name);

}
