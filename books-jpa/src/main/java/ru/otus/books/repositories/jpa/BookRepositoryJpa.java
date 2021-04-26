package ru.otus.books.repositories.jpa;

import org.springframework.stereotype.Repository;
import ru.otus.books.models.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepositoryJpa {
    long count();

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(long id);

    void delete(Book book);

    void updateTitle(Book book, String title);
}
