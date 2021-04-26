package ru.otus.books.repositories.jpa;

import ru.otus.books.models.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookCategoryRepositoryJpa {
    long count();

    BookCategory save(BookCategory bc);

    List<BookCategory> findAll();

    Optional<BookCategory> findById(long id);

    void delete(BookCategory bc);

    void updateTitle(BookCategory bc, String title);
}
